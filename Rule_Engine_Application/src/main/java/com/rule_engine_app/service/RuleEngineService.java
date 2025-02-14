package com.rule_engine_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rule_engine_app.ast.ConditionNode;
import com.rule_engine_app.ast.Node;
import com.rule_engine_app.ast.OperatorNode;
import com.rule_engine_app.model.Rule;
import com.rule_engine_app.model.UserAttributes;
import com.rule_engine_app.repository.RuleRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class RuleEngineService {

    @Autowired
    private RuleRepository ruleRepository;

    public Node createRule(String ruleString) {
        return parseExpression(ruleString);
    }

    private Node parseExpression(String expression) {
        expression = expression.trim();
        // Handling outer parentheses
        if (expression.startsWith("(") && expression.endsWith(")")) {
            expression = expression.substring(1, expression.length() - 1).trim();
        }

        String[] orParts = expression.split("\\s+OR\\s+");
        List<Node> orNodes = new ArrayList<>();

        for (String orPart : orParts) {
            orNodes.add(parseAndExpression(orPart.trim()));
        }

        return orNodes.size() > 1 ? combineOrNodes(orNodes) : orNodes.get(0);
    }

    private Node combineOrNodes(List<Node> nodes) {
        Node combinedNode = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            combinedNode = new OperatorNode("OR", combinedNode, nodes.get(i));
        }
        return combinedNode;
    }

    private Node parseAndExpression(String expression) {
        String[] andParts = expression.split("\\s+AND\\s+");
        List<Node> andNodes = new ArrayList<>();

        for (String andPart : andParts) {
            andNodes.add(parseCondition(andPart.trim()));
        }

        return andNodes.size() > 1 ? combineAndNodes(andNodes) : andNodes.get(0);
    }

    private Node combineAndNodes(List<Node> nodes) {
        Node combinedNode = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            combinedNode = new OperatorNode("AND", combinedNode, nodes.get(i));
        }
        return combinedNode;
    }

    private Node parseCondition(String condition) {
        String[] tokens = condition.split("\\s+");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Invalid condition: " + condition);
        }
        String attribute = tokens[0].trim();
        String operator = tokens[1].trim();
        String value = tokens[2].replace("'", "").trim(); // Remove quotes for string values
        return new ConditionNode(attribute, operator, value);
    }

    public boolean evaluateRule(Node rule, UserAttributes attributes) {
        return rule.evaluate(attributes);
    }

    public Rule saveRule(String ruleString) {
        Rule rule = new Rule();
        rule.setRuleString(ruleString);
        return ruleRepository.save(rule);
    }

    public List<Rule> getAllRules() {
        return ruleRepository.findAll();
    }
}

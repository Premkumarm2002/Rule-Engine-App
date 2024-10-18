package com.rule_engine_app.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.rule_engine_app.ast.Node;
import com.rule_engine_app.model.Rule;
import com.rule_engine_app.model.UserAttributes;
import com.rule_engine_app.service.RuleEngineService;

@RestController
@RequestMapping("/api/rules")
public class RuleEngineController {

    private final RuleEngineService ruleEngineService;

    public RuleEngineController(RuleEngineService ruleEngineService) {
        this.ruleEngineService = ruleEngineService;
    }

    @PostMapping("/create")
    public Rule createRule(@RequestBody String ruleString) {
        return ruleEngineService.saveRule(ruleString);
    }

    @GetMapping("/all")
    public List<Rule> getAllRules() {
        return ruleEngineService.getAllRules();
    }

    @PostMapping("/evaluate")
    public boolean evaluateRule(@RequestBody UserAttributes attributes) {
        Node rule = ruleEngineService.createRule("age > 30 AND department = 'Sales'");
        return ruleEngineService.evaluateRule(rule, attributes);
    }
}

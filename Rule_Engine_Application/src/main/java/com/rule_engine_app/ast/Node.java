package com.rule_engine_app.ast;

import com.rule_engine_app.model.UserAttributes;

public abstract class Node {
    public abstract boolean evaluate(UserAttributes attributes);
}
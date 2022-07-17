package dm.lox;

import dm.lox.Expr.Assign;
import dm.lox.Expr.Binary;
import dm.lox.Expr.Call;
import dm.lox.Expr.Get;
import dm.lox.Expr.Grouping;
import dm.lox.Expr.Literal;
import dm.lox.Expr.Logical;
import dm.lox.Expr.Set;
import dm.lox.Expr.This;
import dm.lox.Expr.Unary;
import dm.lox.Expr.Variable;

class AstPrinter implements Expr.Visitor<String>{
    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Binary expr) {
        return parenthesize(expr.operator.lexeme,expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();
        builder.append("(").append(name);

        for (Expr expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        
        builder.append(")");
        return builder.toString();
    }

    @Override
    public String visitAssignExpr(Assign expr) {
        return parenthesize(expr.name.lexeme, expr.value);
    }

    @Override
    public String visitVariableExpr(Variable expr) {
        return parenthesize(expr.name.lexeme);
    }

    @Override
    public String visitLogicalExpr(Logical expr) {
        return parenthesize(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitCallExpr(Call expr) {
        return parenthesize("function", expr.callee);
    }

    @Override
    public String visitGetExpr(Get expr) {
        return parenthesize(expr.name.lexeme, expr.object);
    }

    @Override
    public String visitSetExpr(Set expr) {
        return parenthesize(expr.name.lexeme, expr.object);
    }

    @Override
    public String visitThisExpr(This expr) {
        return parenthesize(expr.keyword.lexeme);
    }
}

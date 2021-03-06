/* Generated By:JavaCC: Do not edit this line. VelocityParserVisitor.java Version 5.0 */
package com.tsi.netbeans.modules.languages.velocity.jcclexer.node;

import com.tsi.netbeans.modules.languages.velocity.jcclexer.*;

public interface VelocityParserVisitor
{
  public Object visit(SimpleNode node, Object data);
  public Object visit(ASTprocess node, Object data);
  public Object visit(ASTEscapedDirective node, Object data);
  public Object visit(ASTEscape node, Object data);
  public Object visit(ASTComment node, Object data);
  public Object visit(ASTUnparsedContent node, Object data);
  public Object visit(ASTFloatingPointLiteral node, Object data);
  public Object visit(ASTIntegerLiteral node, Object data);
  public Object visit(ASTStringLiteral node, Object data);
  public Object visit(ASTIdentifier node, Object data);
  public Object visit(ASTWord node, Object data);
  public Object visit(ASTDirective node, Object data);
  public Object visit(ASTBlock node, Object data);
  public Object visit(ASTMap node, Object data);
  public Object visit(ASTObjectArray node, Object data);
  public Object visit(ASTIntegerRange node, Object data);
  public Object visit(ASTMethod node, Object data);
  public Object visit(ASTReference node, Object data);
  public Object visit(ASTTrue node, Object data);
  public Object visit(ASTFalse node, Object data);
  public Object visit(ASTText node, Object data);
  public Object visit(ASTForEachStatement node, Object data);
  public Object visit(ASTMacroStatement node, Object data);
  public Object visit(ASTIncludeStatement node, Object data);
  public Object visit(ASTIfStatement node, Object data);
  public Object visit(ASTElseStatement node, Object data);
  public Object visit(ASTElseIfStatement node, Object data);
  public Object visit(ASTSetDirective node, Object data);
  public Object visit(ASTStop node, Object data);
  public Object visit(ASTExpression node, Object data);
  public Object visit(ASTAssignment node, Object data);
  public Object visit(ASTOrNode node, Object data);
  public Object visit(ASTAndNode node, Object data);
  public Object visit(ASTEQNode node, Object data);
  public Object visit(ASTNENode node, Object data);
  public Object visit(ASTLTNode node, Object data);
  public Object visit(ASTGTNode node, Object data);
  public Object visit(ASTLENode node, Object data);
  public Object visit(ASTGENode node, Object data);
  public Object visit(ASTAddNode node, Object data);
  public Object visit(ASTSubtractNode node, Object data);
  public Object visit(ASTMulNode node, Object data);
  public Object visit(ASTDivNode node, Object data);
  public Object visit(ASTModNode node, Object data);
  public Object visit(ASTNotNode node, Object data);
}
/* JavaCC - OriginalChecksum=bcf544a9e3ca37e533560a2f8726de7d (do not edit this line) */

/* Generated By:JJTree: Do not edit this line. ASTForEachStatement.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.tsi.netbeans.modules.languages.velocity.jcclexer.node;

import com.tsi.netbeans.modules.languages.velocity.jcclexer.*;

public
class ASTForEachStatement extends SimpleNode {
  public ASTForEachStatement(int id) {
    super(id);
  }

  public ASTForEachStatement(VelocityParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(VelocityParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=e277d8f5017b68b4ac8339b99d43b940 (do not edit this line) */
/* Generated By:JJTree: Do not edit this line. ASTEQNode.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.tsi.netbeans.modules.languages.velocity.jcclexer.node;

import com.tsi.netbeans.modules.languages.velocity.jcclexer.*;

public
class ASTEQNode extends SimpleNode {
  public ASTEQNode(int id) {
    super(id);
  }

  public ASTEQNode(VelocityParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(VelocityParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=39484e8f21e3372375b85e29bc0bfdc6 (do not edit this line) */
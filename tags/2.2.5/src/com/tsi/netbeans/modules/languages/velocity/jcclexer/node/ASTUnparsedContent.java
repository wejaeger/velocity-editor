/* Generated By:JJTree: Do not edit this line. ASTUnparsedContent.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.tsi.netbeans.modules.languages.velocity.jcclexer.node;

import com.tsi.netbeans.modules.languages.velocity.jcclexer.*;

public
class ASTUnparsedContent extends SimpleNode {
  public ASTUnparsedContent(int id) {
    super(id);
  }

  public ASTUnparsedContent(VelocityParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(VelocityParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=ba59f0b3509f7124fb668db0f63fa4f9 (do not edit this line) */

/* Generated By:JJTree: Do not edit this line. ASTObjectArray.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.tsi.netbeans.modules.languages.velocity.jcclexer.node;

import com.tsi.netbeans.modules.languages.velocity.jcclexer.*;

public
class ASTObjectArray extends SimpleNode {
  public ASTObjectArray(int id) {
    super(id);
  }

  public ASTObjectArray(VelocityParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(VelocityParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=188f23de5e0eaa5ad9e43f24fd6d9d03 (do not edit this line) */

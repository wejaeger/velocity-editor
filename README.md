

Provides basic support to Velocity's *.vm and *.vsl files. Syntax coloring, basic error highlighting and braces matching is achieved with a lexer and parser based on Apache Velocity's 1.6.2 specification and compiled with JavaCC 5.0.
Features

    code folding for #ForEach, #if, #elsif, #else and #macro directives
    syntax coloring for include directive (but no processing of the included template)
    macro calls
    braces matching (#ForEach .. #end, #if .. #end, #elsif .. #end, #else .. #end and #macro .. #end )
    unparsed content (#[[ ]]#)
    error highlighting


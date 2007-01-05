/*
 * (c) Copyright 2006, 2007 Hewlett-Packard Development Company, LP
 * All rights reserved.
 * [See end of file]
 */

package com.hp.hpl.jena.sdb.core.sqlnode;

import com.hp.hpl.jena.query.core.Var;
import com.hp.hpl.jena.sdb.core.Scope;
import com.hp.hpl.jena.sdb.core.ScopeBase;
import com.hp.hpl.jena.sdb.core.sqlexpr.SqlColumn;

/** Root of all concrete tables */

public class SqlTable extends SqlNodeBase
{
    // Alternative: SqlTableValue and SqlTableTriple
    
    private String tableName ;
    protected ScopeBase idScope = null ;
    protected ScopeBase nodeScope = null ;
    
    public SqlTable(String tableName, String aliasName)
    {
        super(aliasName) ;
        this.tableName = tableName ;
        this.idScope = new ScopeBase() ; ;
        this.nodeScope = null ;
    }
    
    @Override
    public boolean isTable() { return true ; }
    @Override
    public SqlTable asTable() { return this ; }
    
    @Override
    public boolean usesColumn(SqlColumn c) { return c.getTable() == this ; }

    public String getTableName()  { return tableName ; }
    
    public void visit(SqlNodeVisitor visitor)
    { visitor.visit(this) ; }

    public Scope getIdScope() { return idScope ; }

    public Scope getNodeScope()
    { 
        if ( nodeScope == null )
            nodeScope = new ScopeBase() ;
        return nodeScope ;
    }
    
    public void setIdColumnForVar(Var var, SqlColumn thisCol)
    {
        if ( idScope == null )
            idScope = new ScopeBase() ;
        idScope.setColumnForVar(var, thisCol) ;
    }
    
    public void setValueColumnForVar(Var var, SqlColumn thisCol)
    {
        if ( nodeScope == null )
            nodeScope = new ScopeBase() ;
        nodeScope.setColumnForVar(var, thisCol) ;
    }
    
    @Override
    public int hashCode()
    {
        int h = tableName.hashCode() ;
        if ( getAliasName() != null )
            h = h ^ getAliasName().hashCode() << 1 ;
        return h ;
    }
    
    @Override
    public boolean equals(Object other)
    {
        if ( ! ( other instanceof SqlTable ) ) 
            return false ;
        SqlTable table = (SqlTable)other ;
        
        if ( ! tableName.equals(table.tableName) )
            return false ;

        if ( getAliasName() == null && table.getAliasName() == null )
            return true ;
        if ( getAliasName() == null || table.getAliasName() == null )
            return false ;
        return getAliasName().equals(table.getAliasName()) ;
    }

}

/*
 * (c) Copyright 2006, 2007 Hewlett-Packard Development Company, LP
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
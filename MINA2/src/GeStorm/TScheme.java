// **********************************************************************
//
// Copyright (c) 2003-2008 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.3.0

package GeStorm;

public final class TScheme implements java.lang.Cloneable
{
    public int id;

    public String name;

    public String content;

    public String memo;

    public TScheme()
    {
    }

    public TScheme(int id, String name, String content, String memo)
    {
        this.id = id;
        this.name = name;
        this.content = content;
        this.memo = memo;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        TScheme _r = null;
        try
        {
            _r = (TScheme)rhs;
        }
        catch(ClassCastException ex)
        {
        }

        if(_r != null)
        {
            if(id != _r.id)
            {
                return false;
            }
            if(name != _r.name && name != null && !name.equals(_r.name))
            {
                return false;
            }
            if(content != _r.content && content != null && !content.equals(_r.content))
            {
                return false;
            }
            if(memo != _r.memo && memo != null && !memo.equals(_r.memo))
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 0;
        __h = 5 * __h + id;
        if(name != null)
        {
            __h = 5 * __h + name.hashCode();
        }
        if(content != null)
        {
            __h = 5 * __h + content.hashCode();
        }
        if(memo != null)
        {
            __h = 5 * __h + memo.hashCode();
        }
        return __h;
    }

    public java.lang.Object
    clone()
    {
        java.lang.Object o = null;
        try
        {
            o = super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return o;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeInt(id);
        __os.writeString(name);
        __os.writeString(content);
        __os.writeString(memo);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        id = __is.readInt();
        name = __is.readString();
        content = __is.readString();
        memo = __is.readString();
    }
}

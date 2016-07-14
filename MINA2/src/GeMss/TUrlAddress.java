// **********************************************************************
//
// Copyright (c) 2003-2008 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.3.0

package GeMss;

public final class TUrlAddress implements java.lang.Cloneable
{
    public String strUrl;

    public TUrlAddress()
    {
    }

    public TUrlAddress(String strUrl)
    {
        this.strUrl = strUrl;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        TUrlAddress _r = null;
        try
        {
            _r = (TUrlAddress)rhs;
        }
        catch(ClassCastException ex)
        {
        }

        if(_r != null)
        {
            if(strUrl != _r.strUrl && strUrl != null && !strUrl.equals(_r.strUrl))
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
        if(strUrl != null)
        {
            __h = 5 * __h + strUrl.hashCode();
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
        __os.writeString(strUrl);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        strUrl = __is.readString();
    }
}

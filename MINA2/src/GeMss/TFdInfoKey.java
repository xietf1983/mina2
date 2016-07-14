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

public final class TFdInfoKey implements java.lang.Cloneable
{
    public String strWorkDomainId;

    public String strFdId;

    public int nFdChannelId;

    public byte byQoS;

    public TFdInfoKey()
    {
    }

    public TFdInfoKey(String strWorkDomainId, String strFdId, int nFdChannelId, byte byQoS)
    {
        this.strWorkDomainId = strWorkDomainId;
        this.strFdId = strFdId;
        this.nFdChannelId = nFdChannelId;
        this.byQoS = byQoS;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        TFdInfoKey _r = null;
        try
        {
            _r = (TFdInfoKey)rhs;
        }
        catch(ClassCastException ex)
        {
        }

        if(_r != null)
        {
            if(strWorkDomainId != _r.strWorkDomainId && strWorkDomainId != null && !strWorkDomainId.equals(_r.strWorkDomainId))
            {
                return false;
            }
            if(strFdId != _r.strFdId && strFdId != null && !strFdId.equals(_r.strFdId))
            {
                return false;
            }
            if(nFdChannelId != _r.nFdChannelId)
            {
                return false;
            }
            if(byQoS != _r.byQoS)
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
        if(strWorkDomainId != null)
        {
            __h = 5 * __h + strWorkDomainId.hashCode();
        }
        if(strFdId != null)
        {
            __h = 5 * __h + strFdId.hashCode();
        }
        __h = 5 * __h + nFdChannelId;
        __h = 5 * __h + (int)byQoS;
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
        __os.writeString(strWorkDomainId);
        __os.writeString(strFdId);
        __os.writeInt(nFdChannelId);
        __os.writeByte(byQoS);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        strWorkDomainId = __is.readString();
        strFdId = __is.readString();
        nFdChannelId = __is.readInt();
        byQoS = __is.readByte();
    }
}

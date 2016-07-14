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

public abstract class _MssWebDisp extends Ice.ObjectImpl implements MssWeb
{
    protected void
    ice_copyStateFrom(Ice.Object __obj)
        throws java.lang.CloneNotSupportedException
    {
        throw new java.lang.CloneNotSupportedException();
    }

    public static final String[] __ids =
    {
        "::GeMss::MssWeb",
        "::Ice::Object"
    };

    public boolean
    ice_isA(String s)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public boolean
    ice_isA(String s, Ice.Current __current)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public String[]
    ice_ids()
    {
        return __ids;
    }

    public String[]
    ice_ids(Ice.Current __current)
    {
        return __ids;
    }

    public String
    ice_id()
    {
        return __ids[0];
    }

    public String
    ice_id(Ice.Current __current)
    {
        return __ids[0];
    }

    public static String
    ice_staticId()
    {
        return __ids[0];
    }

    public final void
    QueryAlarmStorage_async(AMD_MssWeb_QueryAlarmStorage __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime)
    {
        QueryAlarmStorage_async(__cb, bCenter, tFdKey, strAlarmGuid, alarmTime, null);
    }

    public final void
    QueryAlarmStorageV2_async(AMD_MssWeb_QueryAlarmStorageV2 __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, String strUserIP)
    {
        QueryAlarmStorageV2_async(__cb, bCenter, tFdKey, strAlarmGuid, alarmTime, strUserIP, null);
    }

    public final void
    QueryAlarmStorageV3_async(AMD_MssWeb_QueryAlarmStorageV3 __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long nTime, String strUserIP, String storageAreaId)
    {
        QueryAlarmStorageV3_async(__cb, bCenter, tFdKey, strAlarmGuid, nTime, strUserIP, storageAreaId, null);
    }

    public static Ice.DispatchStatus
    ___QueryAlarmStorage(MssWeb __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        __is.startReadEncaps();
        boolean bCenter;
        bCenter = __is.readBool();
        TFdInfoKey tFdKey;
        tFdKey = new TFdInfoKey();
        tFdKey.__read(__is);
        String strAlarmGuid;
        strAlarmGuid = __is.readString();
        long alarmTime;
        alarmTime = __is.readLong();
        __is.endReadEncaps();
        AMD_MssWeb_QueryAlarmStorage __cb = new _AMD_MssWeb_QueryAlarmStorage(__inS);
        try
        {
            __obj.QueryAlarmStorage_async(__cb, bCenter, tFdKey, strAlarmGuid, alarmTime, __current);
        }
        catch(java.lang.Exception ex)
        {
            __cb.ice_exception(ex);
        }
        return Ice.DispatchStatus.DispatchAsync;
    }

    public static Ice.DispatchStatus
    ___QueryAlarmStorageV2(MssWeb __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        __is.startReadEncaps();
        boolean bCenter;
        bCenter = __is.readBool();
        TFdInfoKey tFdKey;
        tFdKey = new TFdInfoKey();
        tFdKey.__read(__is);
        String strAlarmGuid;
        strAlarmGuid = __is.readString();
        long alarmTime;
        alarmTime = __is.readLong();
        String strUserIP;
        strUserIP = __is.readString();
        __is.endReadEncaps();
        AMD_MssWeb_QueryAlarmStorageV2 __cb = new _AMD_MssWeb_QueryAlarmStorageV2(__inS);
        try
        {
            __obj.QueryAlarmStorageV2_async(__cb, bCenter, tFdKey, strAlarmGuid, alarmTime, strUserIP, __current);
        }
        catch(java.lang.Exception ex)
        {
            __cb.ice_exception(ex);
        }
        return Ice.DispatchStatus.DispatchAsync;
    }

    public static Ice.DispatchStatus
    ___QueryAlarmStorageV3(MssWeb __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        __is.startReadEncaps();
        boolean bCenter;
        bCenter = __is.readBool();
        TFdInfoKey tFdKey;
        tFdKey = new TFdInfoKey();
        tFdKey.__read(__is);
        String strAlarmGuid;
        strAlarmGuid = __is.readString();
        long nTime;
        nTime = __is.readLong();
        String strUserIP;
        strUserIP = __is.readString();
        String storageAreaId;
        storageAreaId = __is.readString();
        __is.endReadEncaps();
        AMD_MssWeb_QueryAlarmStorageV3 __cb = new _AMD_MssWeb_QueryAlarmStorageV3(__inS);
        try
        {
            __obj.QueryAlarmStorageV3_async(__cb, bCenter, tFdKey, strAlarmGuid, nTime, strUserIP, storageAreaId, __current);
        }
        catch(java.lang.Exception ex)
        {
            __cb.ice_exception(ex);
        }
        return Ice.DispatchStatus.DispatchAsync;
    }

    private final static String[] __all =
    {
        "QueryAlarmStorage",
        "QueryAlarmStorageV2",
        "QueryAlarmStorageV3",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping"
    };

    public Ice.DispatchStatus
    __dispatch(IceInternal.Incoming in, Ice.Current __current)
    {
        int pos = java.util.Arrays.binarySearch(__all, __current.operation);
        if(pos < 0)
        {
            throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return ___QueryAlarmStorage(this, in, __current);
            }
            case 1:
            {
                return ___QueryAlarmStorageV2(this, in, __current);
            }
            case 2:
            {
                return ___QueryAlarmStorageV3(this, in, __current);
            }
            case 3:
            {
                return ___ice_id(this, in, __current);
            }
            case 4:
            {
                return ___ice_ids(this, in, __current);
            }
            case 5:
            {
                return ___ice_isA(this, in, __current);
            }
            case 6:
            {
                return ___ice_ping(this, in, __current);
            }
        }

        assert(false);
        throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeTypeId(ice_staticId());
        __os.startWriteSlice();
        __os.endWriteSlice();
        super.__write(__os);
    }

    public void
    __read(IceInternal.BasicStream __is, boolean __rid)
    {
        if(__rid)
        {
            __is.readTypeId();
        }
        __is.startReadSlice();
        __is.endReadSlice();
        super.__read(__is, true);
    }

    public void
    __write(Ice.OutputStream __outS)
    {
        Ice.MarshalException ex = new Ice.MarshalException();
        ex.reason = "type GeMss::MssWeb was not generated with stream support";
        throw ex;
    }

    public void
    __read(Ice.InputStream __inS, boolean __rid)
    {
        Ice.MarshalException ex = new Ice.MarshalException();
        ex.reason = "type GeMss::MssWeb was not generated with stream support";
        throw ex;
    }
}

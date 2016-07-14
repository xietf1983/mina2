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

public final class MssWebPrxHelper extends Ice.ObjectPrxHelperBase implements MssWebPrx
{
    public int
    QueryAlarmStorage(boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, TUrlAddressSeqHolder seqUrlAddress)
    {
        return QueryAlarmStorage(bCenter, tFdKey, strAlarmGuid, alarmTime, seqUrlAddress, null, false);
    }

    public int
    QueryAlarmStorage(boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, TUrlAddressSeqHolder seqUrlAddress, java.util.Map<String, String> __ctx)
    {
        return QueryAlarmStorage(bCenter, tFdKey, strAlarmGuid, alarmTime, seqUrlAddress, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private int
    QueryAlarmStorage(boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, TUrlAddressSeqHolder seqUrlAddress, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("QueryAlarmStorage");
                __delBase = __getDelegate(false);
                _MssWebDel __del = (_MssWebDel)__delBase;
                return __del.QueryAlarmStorage(bCenter, tFdKey, strAlarmGuid, alarmTime, seqUrlAddress, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex, null);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, null, __cnt);
            }
        }
    }

    public boolean
    QueryAlarmStorage_async(AMI_MssWeb_QueryAlarmStorage __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime)
    {
        return QueryAlarmStorage_async(__cb, bCenter, tFdKey, strAlarmGuid, alarmTime, null, false);
    }

    public boolean
    QueryAlarmStorage_async(AMI_MssWeb_QueryAlarmStorage __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, java.util.Map<String, String> __ctx)
    {
        return QueryAlarmStorage_async(__cb, bCenter, tFdKey, strAlarmGuid, alarmTime, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private boolean
    QueryAlarmStorage_async(AMI_MssWeb_QueryAlarmStorage __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx &&  __ctx == null)
        {
            __ctx = _emptyContext;
        }
        return __cb.__invoke(this, __cb, bCenter, tFdKey, strAlarmGuid, alarmTime, __ctx);
    }

    public int
    QueryAlarmStorageV2(boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, String strUserIP, TUrlAddressSeqHolder seqUrlAddress)
    {
        return QueryAlarmStorageV2(bCenter, tFdKey, strAlarmGuid, alarmTime, strUserIP, seqUrlAddress, null, false);
    }

    public int
    QueryAlarmStorageV2(boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, String strUserIP, TUrlAddressSeqHolder seqUrlAddress, java.util.Map<String, String> __ctx)
    {
        return QueryAlarmStorageV2(bCenter, tFdKey, strAlarmGuid, alarmTime, strUserIP, seqUrlAddress, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private int
    QueryAlarmStorageV2(boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, String strUserIP, TUrlAddressSeqHolder seqUrlAddress, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("QueryAlarmStorageV2");
                __delBase = __getDelegate(false);
                _MssWebDel __del = (_MssWebDel)__delBase;
                return __del.QueryAlarmStorageV2(bCenter, tFdKey, strAlarmGuid, alarmTime, strUserIP, seqUrlAddress, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex, null);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, null, __cnt);
            }
        }
    }

    public boolean
    QueryAlarmStorageV2_async(AMI_MssWeb_QueryAlarmStorageV2 __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, String strUserIP)
    {
        return QueryAlarmStorageV2_async(__cb, bCenter, tFdKey, strAlarmGuid, alarmTime, strUserIP, null, false);
    }

    public boolean
    QueryAlarmStorageV2_async(AMI_MssWeb_QueryAlarmStorageV2 __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, String strUserIP, java.util.Map<String, String> __ctx)
    {
        return QueryAlarmStorageV2_async(__cb, bCenter, tFdKey, strAlarmGuid, alarmTime, strUserIP, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private boolean
    QueryAlarmStorageV2_async(AMI_MssWeb_QueryAlarmStorageV2 __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, String strUserIP, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx &&  __ctx == null)
        {
            __ctx = _emptyContext;
        }
        return __cb.__invoke(this, __cb, bCenter, tFdKey, strAlarmGuid, alarmTime, strUserIP, __ctx);
    }

    public int
    QueryAlarmStorageV3(boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long nTime, String strUserIP, String storageAreaId, TUrlAddressSeqHolder seqUrlAddress)
    {
        return QueryAlarmStorageV3(bCenter, tFdKey, strAlarmGuid, nTime, strUserIP, storageAreaId, seqUrlAddress, null, false);
    }

    public int
    QueryAlarmStorageV3(boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long nTime, String strUserIP, String storageAreaId, TUrlAddressSeqHolder seqUrlAddress, java.util.Map<String, String> __ctx)
    {
        return QueryAlarmStorageV3(bCenter, tFdKey, strAlarmGuid, nTime, strUserIP, storageAreaId, seqUrlAddress, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private int
    QueryAlarmStorageV3(boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long nTime, String strUserIP, String storageAreaId, TUrlAddressSeqHolder seqUrlAddress, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("QueryAlarmStorageV3");
                __delBase = __getDelegate(false);
                _MssWebDel __del = (_MssWebDel)__delBase;
                return __del.QueryAlarmStorageV3(bCenter, tFdKey, strAlarmGuid, nTime, strUserIP, storageAreaId, seqUrlAddress, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex, null);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, null, __cnt);
            }
        }
    }

    public boolean
    QueryAlarmStorageV3_async(AMI_MssWeb_QueryAlarmStorageV3 __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long nTime, String strUserIP, String storageAreaId)
    {
        return QueryAlarmStorageV3_async(__cb, bCenter, tFdKey, strAlarmGuid, nTime, strUserIP, storageAreaId, null, false);
    }

    public boolean
    QueryAlarmStorageV3_async(AMI_MssWeb_QueryAlarmStorageV3 __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long nTime, String strUserIP, String storageAreaId, java.util.Map<String, String> __ctx)
    {
        return QueryAlarmStorageV3_async(__cb, bCenter, tFdKey, strAlarmGuid, nTime, strUserIP, storageAreaId, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private boolean
    QueryAlarmStorageV3_async(AMI_MssWeb_QueryAlarmStorageV3 __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long nTime, String strUserIP, String storageAreaId, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx &&  __ctx == null)
        {
            __ctx = _emptyContext;
        }
        return __cb.__invoke(this, __cb, bCenter, tFdKey, strAlarmGuid, nTime, strUserIP, storageAreaId, __ctx);
    }

    public static MssWebPrx
    checkedCast(Ice.ObjectPrx __obj)
    {
        MssWebPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (MssWebPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::GeMss::MssWeb"))
                {
                    MssWebPrxHelper __h = new MssWebPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static MssWebPrx
    checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        MssWebPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (MssWebPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::GeMss::MssWeb", __ctx))
                {
                    MssWebPrxHelper __h = new MssWebPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static MssWebPrx
    checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        MssWebPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::GeMss::MssWeb"))
                {
                    MssWebPrxHelper __h = new MssWebPrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static MssWebPrx
    checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        MssWebPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::GeMss::MssWeb", __ctx))
                {
                    MssWebPrxHelper __h = new MssWebPrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static MssWebPrx
    uncheckedCast(Ice.ObjectPrx __obj)
    {
        MssWebPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (MssWebPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                MssWebPrxHelper __h = new MssWebPrxHelper();
                __h.__copyFrom(__obj);
                __d = __h;
            }
        }
        return __d;
    }

    public static MssWebPrx
    uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        MssWebPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            MssWebPrxHelper __h = new MssWebPrxHelper();
            __h.__copyFrom(__bb);
            __d = __h;
        }
        return __d;
    }

    protected Ice._ObjectDelM
    __createDelegateM()
    {
        return new _MssWebDelM();
    }

    protected Ice._ObjectDelD
    __createDelegateD()
    {
        return new _MssWebDelD();
    }

    public static void
    __write(IceInternal.BasicStream __os, MssWebPrx v)
    {
        __os.writeProxy(v);
    }

    public static MssWebPrx
    __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            MssWebPrxHelper result = new MssWebPrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }
}

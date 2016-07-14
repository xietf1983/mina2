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

public abstract class AMI_MssWeb_QueryAlarmStorageV2 extends IceInternal.OutgoingAsync
{
    public abstract void ice_response(int __ret, TUrlAddress[] seqUrlAddress);
    public abstract void ice_exception(Ice.LocalException ex);

    public final boolean
    __invoke(Ice.ObjectPrx __prx, AMI_MssWeb_QueryAlarmStorageV2 __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, String strUserIP, java.util.Map<String, String> __ctx)
    {
        __acquireCallback(__prx);
        try
        {
            ((Ice.ObjectPrxHelperBase)__prx).__checkTwowayOnly("QueryAlarmStorageV2");
            __prepare(__prx, "QueryAlarmStorageV2", Ice.OperationMode.Normal, __ctx);
            __os.writeBool(bCenter);
            tFdKey.__write(__os);
            __os.writeString(strAlarmGuid);
            __os.writeLong(alarmTime);
            __os.writeString(strUserIP);
            __os.endWriteEncaps();
            return __send();
        }
        catch(Ice.LocalException __ex)
        {
            __releaseCallback(__ex);
            return false;
        }
    }

    protected final void
    __response(boolean __ok)
    {
        TUrlAddress[] seqUrlAddress;
        int __ret;
        try
        {
            if(!__ok)
            {
                try
                {
                    __throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name());
                }
            }
            __is.startReadEncaps();
            seqUrlAddress = TUrlAddressSeqHelper.read(__is);
            __ret = __is.readInt();
            __is.endReadEncaps();
        }
        catch(Ice.LocalException __ex)
        {
            __finished(__ex);
            return;
        }
        ice_response(__ret, seqUrlAddress);
        __releaseCallback();
    }
}

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

public abstract class AMI_WebSender_trigScheme extends IceInternal.OutgoingAsync
{
    public abstract void ice_response();
    public abstract void ice_exception(Ice.LocalException ex);

    public final boolean
    __invoke(Ice.ObjectPrx __prx, AMI_WebSender_trigScheme __cb, String triggerDescribe, TScheme[] schemes, String[] userIds, java.util.Map<String, String> __ctx)
    {
        __acquireCallback(__prx);
        try
        {
            __prepare(__prx, "trigScheme", Ice.OperationMode.Idempotent, __ctx);
            __os.writeString(triggerDescribe);
            TSchemeArrayHelper.write(__os, schemes);
            Ice.StringSeqHelper.write(__os, userIds);
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
            __is.skipEmptyEncaps();
        }
        catch(Ice.LocalException __ex)
        {
            __finished(__ex);
            return;
        }
        ice_response();
        __releaseCallback();
    }
}

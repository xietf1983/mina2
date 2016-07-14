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

public final class WebSenderPrxHelper extends Ice.ObjectPrxHelperBase implements WebSenderPrx
{
    public void
    trigScheme(String triggerDescribe, TScheme[] schemes, String[] userIds)
    {
        trigScheme(triggerDescribe, schemes, userIds, null, false);
    }

    public void
    trigScheme(String triggerDescribe, TScheme[] schemes, String[] userIds, java.util.Map<String, String> __ctx)
    {
        trigScheme(triggerDescribe, schemes, userIds, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private void
    trigScheme(String triggerDescribe, TScheme[] schemes, String[] userIds, java.util.Map<String, String> __ctx, boolean __explicitCtx)
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
                __delBase = __getDelegate(false);
                _WebSenderDel __del = (_WebSenderDel)__delBase;
                __del.trigScheme(triggerDescribe, schemes, userIds, __ctx);
                return;
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __cnt = __handleExceptionWrapperRelaxed(__delBase, __ex, null, __cnt);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, null, __cnt);
            }
        }
    }

    public boolean
    trigScheme_async(AMI_WebSender_trigScheme __cb, String triggerDescribe, TScheme[] schemes, String[] userIds)
    {
        return trigScheme_async(__cb, triggerDescribe, schemes, userIds, null, false);
    }

    public boolean
    trigScheme_async(AMI_WebSender_trigScheme __cb, String triggerDescribe, TScheme[] schemes, String[] userIds, java.util.Map<String, String> __ctx)
    {
        return trigScheme_async(__cb, triggerDescribe, schemes, userIds, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private boolean
    trigScheme_async(AMI_WebSender_trigScheme __cb, String triggerDescribe, TScheme[] schemes, String[] userIds, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx &&  __ctx == null)
        {
            __ctx = _emptyContext;
        }
        return __cb.__invoke(this, __cb, triggerDescribe, schemes, userIds, __ctx);
    }

    public static WebSenderPrx
    checkedCast(Ice.ObjectPrx __obj)
    {
        WebSenderPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (WebSenderPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::GeStorm::WebSender"))
                {
                    WebSenderPrxHelper __h = new WebSenderPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static WebSenderPrx
    checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        WebSenderPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (WebSenderPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::GeStorm::WebSender", __ctx))
                {
                    WebSenderPrxHelper __h = new WebSenderPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static WebSenderPrx
    checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        WebSenderPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::GeStorm::WebSender"))
                {
                    WebSenderPrxHelper __h = new WebSenderPrxHelper();
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

    public static WebSenderPrx
    checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        WebSenderPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::GeStorm::WebSender", __ctx))
                {
                    WebSenderPrxHelper __h = new WebSenderPrxHelper();
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

    public static WebSenderPrx
    uncheckedCast(Ice.ObjectPrx __obj)
    {
        WebSenderPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (WebSenderPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                WebSenderPrxHelper __h = new WebSenderPrxHelper();
                __h.__copyFrom(__obj);
                __d = __h;
            }
        }
        return __d;
    }

    public static WebSenderPrx
    uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        WebSenderPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            WebSenderPrxHelper __h = new WebSenderPrxHelper();
            __h.__copyFrom(__bb);
            __d = __h;
        }
        return __d;
    }

    protected Ice._ObjectDelM
    __createDelegateM()
    {
        return new _WebSenderDelM();
    }

    protected Ice._ObjectDelD
    __createDelegateD()
    {
        return new _WebSenderDelD();
    }

    public static void
    __write(IceInternal.BasicStream __os, WebSenderPrx v)
    {
        __os.writeProxy(v);
    }

    public static WebSenderPrx
    __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            WebSenderPrxHelper result = new WebSenderPrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }
}

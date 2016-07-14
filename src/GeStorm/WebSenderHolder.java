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

public final class WebSenderHolder
{
    public
    WebSenderHolder()
    {
    }

    public
    WebSenderHolder(WebSender value)
    {
        this.value = value;
    }

    public class Patcher implements IceInternal.Patcher
    {
        public void
        patch(Ice.Object v)
        {
            try
            {
                value = (WebSender)v;
            }
            catch(ClassCastException ex)
            {
                IceInternal.Ex.throwUOE(type(), v.ice_id());
            }
        }

        public String
        type()
        {
            return "::GeStorm::WebSender";
        }
    }

    public Patcher
    getPatcher()
    {
        return new Patcher();
    }

    public WebSender value;
}

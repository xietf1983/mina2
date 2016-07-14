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

final class _AMD_WebSender_trigScheme extends IceInternal.IncomingAsync implements AMD_WebSender_trigScheme
{
    public
    _AMD_WebSender_trigScheme(IceInternal.Incoming in)
    {
        super(in);
    }

    public void
    ice_response()
    {
        if(__validateResponse(true))
        {
            __response(true);
        }
    }

    public void
    ice_exception(java.lang.Exception ex)
    {
        if(__validateException(ex))
        {
            __exception(ex);
        }
    }
}

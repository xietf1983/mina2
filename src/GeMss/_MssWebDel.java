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

public interface _MssWebDel extends Ice._ObjectDel
{
    int QueryAlarmStorage(boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, TUrlAddressSeqHolder seqUrlAddress, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int QueryAlarmStorageV2(boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, String strUserIP, TUrlAddressSeqHolder seqUrlAddress, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int QueryAlarmStorageV3(boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long nTime, String strUserIP, String storageAreaId, TUrlAddressSeqHolder seqUrlAddress, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;
}

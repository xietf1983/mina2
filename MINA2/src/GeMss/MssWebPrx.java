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

public interface MssWebPrx extends Ice.ObjectPrx
{
    public int QueryAlarmStorage(boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, TUrlAddressSeqHolder seqUrlAddress);
    public int QueryAlarmStorage(boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, TUrlAddressSeqHolder seqUrlAddress, java.util.Map<String, String> __ctx);

    public boolean QueryAlarmStorage_async(AMI_MssWeb_QueryAlarmStorage __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime);
    public boolean QueryAlarmStorage_async(AMI_MssWeb_QueryAlarmStorage __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, java.util.Map<String, String> __ctx);

    public int QueryAlarmStorageV2(boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, String strUserIP, TUrlAddressSeqHolder seqUrlAddress);
    public int QueryAlarmStorageV2(boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, String strUserIP, TUrlAddressSeqHolder seqUrlAddress, java.util.Map<String, String> __ctx);

    public boolean QueryAlarmStorageV2_async(AMI_MssWeb_QueryAlarmStorageV2 __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, String strUserIP);
    public boolean QueryAlarmStorageV2_async(AMI_MssWeb_QueryAlarmStorageV2 __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, String strUserIP, java.util.Map<String, String> __ctx);

    public int QueryAlarmStorageV3(boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long nTime, String strUserIP, String storageAreaId, TUrlAddressSeqHolder seqUrlAddress);
    public int QueryAlarmStorageV3(boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long nTime, String strUserIP, String storageAreaId, TUrlAddressSeqHolder seqUrlAddress, java.util.Map<String, String> __ctx);

    public boolean QueryAlarmStorageV3_async(AMI_MssWeb_QueryAlarmStorageV3 __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long nTime, String strUserIP, String storageAreaId);
    public boolean QueryAlarmStorageV3_async(AMI_MssWeb_QueryAlarmStorageV3 __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long nTime, String strUserIP, String storageAreaId, java.util.Map<String, String> __ctx);
}

package com.ghostchu.peerbanhelper.btn.ping.legacy;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class LegacyBtnPeerHistoryPing {
    @SerializedName("populate_time")
    private long populateTime;
    @SerializedName("peers")
    private List<LegacyBtnPeerHistory> peers;

}
package com.smarttrade.demo.core.chooser;

import com.smarttrade.demo.core.Currency;
import com.smarttrade.demo.core.sdp.Sdp;

public interface SdpChooser {
    Sdp choose(Currency currency, String clientName);
}

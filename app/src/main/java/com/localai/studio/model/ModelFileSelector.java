package com.localai.studio.model;

import java.util.List;

public class ModelFileSelector {
    public String pickBestQuant(List<String> filenames, boolean preferQuality) {
        String first = null;
        for (String f : filenames) {
            if (first == null) first = f;
            String lower = f.toLowerCase();
            if (lower.contains("f16")) continue;
            if (!preferQuality && lower.contains("q4_k_m")) return f;
            if (preferQuality && lower.contains("q5_k_m")) return f;
            if (lower.contains("q3_k_m") || lower.contains("iq4")) first = f;
        }
        return first;
    }
}

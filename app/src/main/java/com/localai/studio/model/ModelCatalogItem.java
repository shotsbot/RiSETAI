package com.localai.studio.model;

public class ModelCatalogItem {
    public String name;
    public String repo;
    public String format;
    public String recommended_quant;
    public String category;
    public String use_case;

    public boolean isHeavyFor8Gb() {
        String low = (name + " " + category + " " + use_case).toLowerCase();
        return low.contains("7b") || low.contains("8b") || low.contains("berat");
    }

    public String buildRepoUrl() {
        return "https://huggingface.co/" + repo;
    }
}

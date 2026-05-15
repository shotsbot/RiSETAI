package com.localai.studio.model;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ModelCatalogRepository {
    private final Context context;
    public ModelCatalogRepository(Context context) { this.context = context; }
    public String loadCatalogJson() throws IOException {
        InputStream in = context.getAssets().open("models/catalog.json");
        return new String(in.readAllBytes(), StandardCharsets.UTF_8);
    }
}

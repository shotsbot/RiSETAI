package com.localai.studio.editor;

import java.io.File;
import java.nio.file.Files;

public class EditorFileManager {
    public String read(File f) throws Exception { return Files.readString(f.toPath()); }
    public void write(File f, String c) throws Exception { Files.writeString(f.toPath(), c); }
}

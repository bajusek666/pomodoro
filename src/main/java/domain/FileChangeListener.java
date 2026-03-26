package domain;

import java.io.File;

public interface FileChangeListener {
    void onChange(File file);
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile implements Container {
    private final String[] fileContent = new String[100];
    private Integer length = 0;

    public ReadFile(String file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int i = 0;
            br.readLine();
            while ((st = br.readLine()) != null) {
                fileContent[i++] = st;
                length++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterator getIterator() {
        return new ReadFileIterator();
    }

    private class ReadFileIterator implements Iterator {
        private int index;

        @Override
        public boolean hasNext() {
            return index < length;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return fileContent[index++];
            }
            return null;
        }
    }
}

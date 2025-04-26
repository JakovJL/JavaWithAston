package Module_3;

import Module_3.Exeptions.FileReadException;
import Module_3.Exeptions.FileWriteException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;


public class ReadWrite {


    public static String readFromFile(String fileName) throws FileReadException {
        try (RandomAccessFile file = new RandomAccessFile(fileName, "r");
             FileChannel channel = file.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(512);
            StringBuilder stringBuilder = new StringBuilder();

            int byteRead = channel.read(buffer);
            while (byteRead > 0) {
                buffer.flip();

                byte[] bytes = new byte[buffer.limit()];
                buffer.get(bytes);

                String str = new String(bytes, StandardCharsets.UTF_8);
                stringBuilder.append(str);

                buffer.clear();
                byteRead = channel.read(buffer);
            }

            return stringBuilder.toString();

        } catch (FileNotFoundException e) {
            throw new FileReadException("Файл " + fileName + " не найден", e);
        } catch (IOException e) {
            throw new FileReadException("Ошибка при чтении из файла " + fileName, e);
        }
    }

    /**
     * @param append флаг:
     *                true - добавить в конец файла,
     *                false - перезаписать файл
     **/
    public static void writeToFile(String fileName, String content, boolean append) throws FileWriteException {
        try (RandomAccessFile file = new RandomAccessFile(fileName, "rw");
             FileChannel channel = file.getChannel()) {

            if (append) {
                channel.position(channel.size());
            }

            ByteBuffer buffer = ByteBuffer.wrap(content.getBytes(StandardCharsets.UTF_8));
            channel.write(buffer);

        } catch (FileNotFoundException e) {
            throw new FileWriteException("Невозможно создать или открыть файл " + fileName, e);
        } catch (IOException e) {
            throw new FileWriteException("Ошибка при записи в файл " + fileName, e);
        }
    }

    public static void main(String[] args) {
        String fileName = "TestText.txt";
        String poem = "\nК Чаадаеву\n" +
                "Любви, надежды, тихой славы\n" +
                "Недолго нежил нас обман,\n" +
                "Исчезли юные забавы,\n" +
                "Как сон, как утренний туман;\n" +
                "Но в нас горит еще желанье,\n" +
                "Под гнетом власти роковой\n" +
                "Нетерпеливою душой\n" +
                "Отчизны внемлем призыванье.\n" +
                "Мы ждем с томленьем упованья\n" +
                "Минуты вольности святой,\n" +
                "Как ждет любовник молодой\n" +
                "Минуты верного свиданья.\n" +
                "Пока свободою горим,\n" +
                "Пока сердца для чести живы,\n" +
                "Мой друг, отчизне посвятим\n" +
                "Души прекрасные порывы!\n" +
                "Товарищ, верь: взойдет она,\n" +
                "Звезда пленительного счастья,\n" +
                "Россия вспрянет ото сна,\n" +
                "И на обломках самовластья\n" +
                "Напишут наши имена!";

        try {

            writeToFile(fileName, poem, true);
            System.out.println("Запись в файл успешно выполнена");

            String fileContent = readFromFile(fileName);
            System.out.println("Содержимое файла:");
            System.out.println(fileContent);

        } catch (FileReadException e) {
            System.err.println("Ошибка чтения: " + e.getMessage());
            e.printStackTrace();
        } catch (FileWriteException e) {
            System.err.println("Ошибка записи: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Неожиданная ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
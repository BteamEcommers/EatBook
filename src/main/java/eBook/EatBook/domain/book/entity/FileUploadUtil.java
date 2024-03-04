package eBook.EatBook.domain.book.entity;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadUtil {   //이미지를 업로드하려면 필요한 클래스, 2일전에 만들어서 설명이 기억 잘 안납니다.
    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try {
            Path filePath = uploadPath.resolve(fileName);
            multipartFile.transferTo(filePath.toFile());
        } catch (IOException ex) {
            throw new IOException("이미지 업로드에 실패했습니다.");
        }
    }
}

package ro.dg.springbootapp.springbootapp.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ro.dg.springbootapp.springbootapp.domain.model.Image;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ImageService {

    private static String UPLOAD_ROOT = "upload-dir";

    private final ResourceLoader resourceLoader;

    public ImageService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    public Flux<Image> findAllImages(){
        try {
           return Flux.fromIterable(
                    Files.newDirectoryStream(Paths.get(UPLOAD_ROOT)))
                    .map(path -> new Image(path.hashCode(), path.getFileName().toString()));

        } catch (IOException e) {
            return Flux.empty();
        }
    }

    public Mono<Resource> findOneImage(String filename){
        return Mono.fromSupplier(() -> resourceLoader.getResource("file:"
                +UPLOAD_ROOT+"/"+filename));
    }

    public Mono<Void> createImage(Flux<FilePart> files){
        return files.flatMap((FilePart filePart) -> {

//                    Mono<Void> copyFile = Mono.just(
//                            Paths.get(UPLOAD_ROOT, filePart.filename()).toFile())
////                            .log("createImage-picktarget")
//                            .map(destFile -> {
//                                try {
//                                    destFile.createNewFile();
//                                    return destFile;
//                                } catch (IOException e) {
//                                    throw new RuntimeException(e);
//                                }
//                            })
////                            .log("createImage-newfile")
//                            .flatMap(filePart::transferTo)
//                            .log("createImage-copy");

             return Mono.just(
                    Paths.get(UPLOAD_ROOT,filePart.filename()).toFile())
                    .map(destFile -> {
                        try {
                            destFile.createNewFile();
                            return destFile;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }).flatMap(file-> filePart.transferTo(file));
        }).then();



//        return files.flatMap(filePart -> filePart.transferTo(Paths.get(UPLOAD_ROOT,
//                filePart.filename()).toFile()))
//                .then();
    }

    public Mono<Void> deleteImage(String filename){
       return Mono.fromRunnable(()-> {
            try {
                Files.deleteIfExists(Paths.get(UPLOAD_ROOT, filename));
            } catch (IOException e) {
                throw  new RuntimeException(e);
            }
        });
    }

    @Bean
    CommandLineRunner setUp() {
        return (args) -> {
            FileSystemUtils.deleteRecursively(new File(UPLOAD_ROOT));

            Files.createDirectory(Paths.get(UPLOAD_ROOT));

            FileCopyUtils.copy("Test file",
                    new FileWriter(UPLOAD_ROOT +
                            "/learning-spring-boot-cover.jpg"));

            FileCopyUtils.copy("Test file2",
                    new FileWriter(UPLOAD_ROOT +
                            "/learning-spring-boot-2nd-edition-cover.jpg"));

            FileCopyUtils.copy("Test file3",
                    new FileWriter(UPLOAD_ROOT + "/bazinga.png"));
        };
    }

}

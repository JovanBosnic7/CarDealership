package rs.ac.uns.ftn.carDealership.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.carDealership.model.vehicle.ImageModel;
import rs.ac.uns.ftn.carDealership.repository.ImageRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.Deflater;

@Service
public class ImageFactory {
    @Autowired
    ImageRepository imageRepository;

    public void uploadImages(ArrayList<MultipartFile> images) throws IOException {
        for (MultipartFile multipartFile : images) {
            ImageModel imageModel = new ImageModel();
            imageModel.setName(multipartFile.getOriginalFilename());
            imageModel.setType(multipartFile.getContentType());
            imageModel.setPicByte(compressBytes(multipartFile.getBytes()));
            imageRepository.save(imageModel);
        }
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

}

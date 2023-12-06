//package com.rentalHive.rentalHive.service.implementations;
//
//import com.rentalHive.rentalHive.model.entities.Image;
//import com.rentalHive.rentalHive.repository.IImageRepo;
//import com.rentalHive.rentalHive.service.IFilesStorageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class ImagesStorageServiceImpl implements IFilesStorageService {
//
//    @Autowired
//    private IImageRepo imageRepository;
//
//    @Override
//    public Image create(Image image) {
//        return imageRepository.save(image);
//    }
//    @Override
//    public List<Image> viewAll() {
//        return (List<Image>) imageRepository.findAll();
//    }
//    @Override
//    public Image viewById(long id) {
//        return imageRepository.findById(id).get();
//    }
//}

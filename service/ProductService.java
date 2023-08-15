package com.icia.mgs.service;

import com.icia.mgs.dao.*;
import com.icia.mgs.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private ModelAndView mav;
    private final ProductRepository prepo;
    private final CompanyRepository crepo;
    private final ProductDAO pdao;
    private final PLikeRepository plrepo;
    private final PLikeDAO pldao;
    private Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/product");


    // 상품 등록 페이지 이동
    public ModelAndView pRegiForm(int cNum) {
        mav = new ModelAndView();
        Optional<CompanyEntity> entity = crepo.findById(cNum);

        CompanyDTO company = new CompanyDTO();
        company = CompanyDTO.toDTO(entity.get());

        mav.setViewName("product/register");
        mav.addObject("company", company);

        return mav;
    }

    // 상품 등록
    public ModelAndView pRegister(ProductDTO product) {
        mav = new ModelAndView();

        MultipartFile pmPic = product.getPmPic();
        MultipartFile piPic1 = product.getPiPic1();
        MultipartFile piPic2 = product.getPiPic2();
        MultipartFile piPic3 = product.getPiPic3();

        if (!pmPic.isEmpty()) {
            Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/product");

            String uuid = UUID.randomUUID().toString().substring(0, 8);
            String originalFileName = pmPic.getOriginalFilename();
            String pmPicName = uuid + "_" + originalFileName;

            product.setPmPicName(pmPicName);

            String savePath = path + "/" + pmPicName;

            try {
                pmPic.transferTo(new File(savePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (!piPic1.isEmpty()) {
            Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/product");

            String uuid = UUID.randomUUID().toString().substring(0, 8);
            String originalFileName = piPic1.getOriginalFilename();
            String piPicName1 = uuid + "_" + originalFileName;

            product.setPiPicName1(piPicName1);

            String savePath = path + "/" + piPicName1;

            try {
                piPic1.transferTo(new File(savePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (!piPic2.isEmpty()) {
            Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/product");

            String uuid = UUID.randomUUID().toString().substring(0, 8);
            String originalFileName = piPic2.getOriginalFilename();
            String piPicName2 = uuid + "_" + originalFileName;

            product.setPiPicName2(piPicName2);

            String savePath = path + "/" + piPicName2;

            try {
                piPic2.transferTo(new File(savePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (!piPic3.isEmpty()) {
            Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/product");

            String uuid = UUID.randomUUID().toString().substring(0, 8);
            String originalFileName = piPic3.getOriginalFilename();
            String piPicName3 = uuid + "_" + originalFileName;

            product.setPiPicName3(piPicName3);

            String savePath = path + "/" + piPicName3;

            try {
                piPic3.transferTo(new File(savePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ProductEntity entity = ProductEntity.toEntity(product);

        prepo.save(entity);
        mav.setViewName("index");

        return mav;
    }

    // 상품 목록(페이징)
    public List<ProductDTO> pPagingList() {
        List<ProductDTO> productList = new ArrayList<>();

        List<ProductEntity> entityList = prepo.findAll();

        for (ProductEntity entity : entityList) {
            productList.add(ProductDTO.toDTO(entity));
        }

        return pdao.productList();
    }

    // 상품 검색
    public List<ProductDTO> pSearchList(SearchDTO search) {
        return pdao.pSearchList(search);
    }

    // 상품 정보 상세보기
    public ModelAndView pView(int pNum) {
        mav = new ModelAndView();

        ProductDTO product = pdao.pView(pNum);

        mav.addObject("view", product);
        mav.setViewName("product/view");

        return mav;
    }

    // 상품 수정 페이지 이동
    public ModelAndView pModiForm(int pNum) {
        mav = new ModelAndView();

        ProductDTO product = (ProductDTO) pView(pNum).getModel().get("view");

        List<CompanyEntity> entityList = crepo.findAll();


        mav.addObject("company", entityList);
        mav.setViewName("product/modify");
        mav.addObject("modify", product);

        return mav;
    }

    // 상품 수정
    public ModelAndView pModify(ProductDTO product) {
        mav = new ModelAndView();

        // 메인사진
        if (!product.getPmPicName().isEmpty()) {
            String delPath = path + "/" + product.getPmPicName();

            File delFile = new File(delPath);

            if (delFile.exists()) {
                delFile.delete();
            }
        }

        MultipartFile pmPic = product.getPmPic();

        if (!pmPic.isEmpty()) {
            String uuid = UUID.randomUUID().toString().substring(0, 8);
            String originalFileName = pmPic.getOriginalFilename();
            String pmPicName = uuid + "_" + originalFileName;

            product.setPmPicName(pmPicName);

            String savePath = path + "/" + pmPicName;

            try {
                pmPic.transferTo(new File(savePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 상세사진1
        if (!product.getPiPicName1().isEmpty()) {
            String delPath = path + "/" + product.getPiPicName1();

            File delFile = new File(delPath);

            if (delFile.exists()) {
                delFile.delete();
            }
        }

        MultipartFile piPic1 = product.getPiPic1();

        if (!piPic1.isEmpty()) {
            String uuid = UUID.randomUUID().toString().substring(0, 8);
            String originalFileName = piPic1.getOriginalFilename();
            String piPicName1 = uuid + "_" + originalFileName;

            product.setPiPicName1(piPicName1);

            String savePath = path + "/" + piPicName1;

            try {
                piPic1.transferTo(new File(savePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 상세사진2
        if (!product.getPiPicName2().isEmpty()) {
            String delPath = path + "/" + product.getPiPicName2();

            File delFile = new File(delPath);

            if (delFile.exists()) {
                delFile.delete();
            }
        }

        MultipartFile piPic2 = product.getPiPic2();

        if (!piPic2.isEmpty()) {
            String uuid = UUID.randomUUID().toString().substring(0, 8);
            String originalFileName = piPic2.getOriginalFilename();
            String piPicName2 = uuid + "_" + originalFileName;

            product.setPiPicName2(piPicName2);

            String savePath = path + "/" + piPicName2;

            try {
                piPic2.transferTo(new File(savePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 상세사진3
        if (!product.getPiPicName3().isEmpty()) {
            String delPath = path + "/" + product.getPiPicName3();

            File delFile = new File(delPath);

            if (delFile.exists()) {
                delFile.delete();
            }
        }

        MultipartFile piPic3 = product.getPiPic3();

        if (!piPic3.isEmpty()) {
            String uuid = UUID.randomUUID().toString().substring(0, 8);
            String originalFileName = piPic3.getOriginalFilename();
            String piPicName3 = uuid + "_" + originalFileName;

            product.setPiPicName3(piPicName3);

            String savePath = path + "/" + piPicName3;

            try {
                piPic3.transferTo(new File(savePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // DTO → Entity로 변형
        ProductEntity entity = ProductEntity.toEntity(product);

        // 수정
        prepo.save(entity);

        mav.setViewName("redirect:/pView/" + product.getPNum());
        return mav;
    }

    // 상품 삭제
    public ModelAndView pDelete(int pNum) {
        mav = new ModelAndView();
        ProductDTO product = (ProductDTO) pView(pNum).getModel().get("view");

        prepo.deleteById(pNum);

        mav.setViewName("redirect:/pList");
        return mav;
    }



}

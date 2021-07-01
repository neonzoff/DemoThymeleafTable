package com.neonzoff.DemoThymeleaf.controller;

import java.util.Optional;
import java.util.Random;

import com.neonzoff.DemoThymeleaf.dao.UserRepository;
import com.neonzoff.DemoThymeleaf.model.PagerModel;
import com.neonzoff.DemoThymeleaf.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@Controller
public class UserController {

    private static final int COUNT_OF_RECORDS = 33;
    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 15, 30, 50};

    @Autowired
    UserRepository repository;
    private final Random random = new Random();

    @GetMapping("/")
    public ModelAndView homepage(@RequestParam("pageSize") Optional<Integer> pageSize,
                                 @RequestParam("page") Optional<Integer> page) {

        if (repository.count() != 0) {
            ;//pass
        } else {
            addtorepository(COUNT_OF_RECORDS);
        }

        ModelAndView modelAndView = new ModelAndView("index");
        //
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
//        System.out.println("here is client repo " + repository.findAll());
        Page<UserModel> userList = repository.findAll(new PageRequest(evalPage, evalPageSize));
//        System.out.println("client list get total pages" + userList.getTotalPages() + "client list get number " + userList.getNumber());
        PagerModel pager = new PagerModel(userList.getTotalPages(), userList.getNumber(), BUTTONS_TO_SHOW);
        // add clientmodel
        modelAndView.addObject("userList", userList);
        // evaluate page size
        modelAndView.addObject("selectedPageSize", evalPageSize);
        // add page sizes
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        // add pager
        modelAndView.addObject("pager", pager);
        return modelAndView;

    }


    private void addtorepository(int count) {
        for (int i = 0; i < count; i++) {
            UserModel user = new UserModel();
            user.setSurname(getRandomNick(8));
            user.setName(getRandomNick(5));
            repository.save(user);
        }
    }

    private String getRandomNick(int length) {
        StringBuilder builder = new StringBuilder();
        final String RUS = "абвгдеежзиклмнопрстуфхцчшщы";
        final String DIGITS = "0123456789";
        char[] alphabet = (RUS + RUS.toUpperCase() + DIGITS).toCharArray();
        for (int i = 0; i < length; i++) {
            builder.append(alphabet[random.nextInt(alphabet.length)]);
        }
        return builder.toString();
    }

}

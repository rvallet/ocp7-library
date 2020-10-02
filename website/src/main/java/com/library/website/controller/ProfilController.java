package com.library.website.controller;

import com.library.website.beans.BookLoanBean;
import com.library.website.beans.UserBean;
import com.library.website.proxies.MicroServiceLibraryProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProfilController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfilController.class);

    @Autowired
    MicroServiceLibraryProxy msLibraryProxy;

    @GetMapping("/user/profil")
    public String userProfil(Model model) {
        UserBean u = msLibraryProxy.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user" , u );

        List<BookLoanBean> bookLoanList = msLibraryProxy.getBookLoansByUserId(u.getId().toString());
        model.addAttribute("bookLoanList" , bookLoanList);

        LOGGER.debug("bookLoanList : Size = {} (id du premier = {})",
            bookLoanList.size(),
            bookLoanList.isEmpty() ? "aucun" : bookLoanList.get(0).getId()
        );
        LOGGER.info("Chargement du profil {}", u.getEmail());

        return "user/profil";
    }

    @GetMapping("/user/update-bookloan")
    public String updateUserBookLoan(
            @RequestParam (name="id") Long bookLoanId,
            @RequestParam (name="action", required = false) String action,
            Model model) {
        UserBean u = msLibraryProxy.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        BookLoanBean bl = msLibraryProxy.getBookLoanById(bookLoanId);

        LOGGER.debug("{} =={} --> {}", bl.getUser().getId().toString(), u.getId().toString(), bl.getUser().getId().toString().equals(u.getId().toString()));
        if (bl.getUser().getId().toString().equals(u.getId().toString()) && "loanextended".equalsIgnoreCase(action)) {
            msLibraryProxy.extendBookLoan(bookLoanId);
            LOGGER.debug("Mise à jour de l'emprunt id {}", bookLoanId);
        }

        if (!bl.getUser().getId().toString().equals(u.getId().toString())) {
            LOGGER.warn("ALERTE ! Tentative de mise à de l'emprunt id {} par l'utilisateur {}", bookLoanId, u.getEmail());
        }

        List<BookLoanBean> bookLoanList = msLibraryProxy.getBookLoansByUserId(u.getId().toString());
        model.addAttribute("bookLoanList" , bookLoanList);

        return "redirect:/user/profil#nav-bookloan";
    }

    @GetMapping("/admin/update-bookloan")
    public String updateUserBookLoanAdmin(
            @RequestParam (name="id") Long bookLoanId,
            @RequestParam (name="action", required = false) String action,
            Model model) {
        UserBean u = msLibraryProxy.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        BookLoanBean bl = msLibraryProxy.getBookLoanById(bookLoanId);

        if ("loanextended".equalsIgnoreCase(action)) {
            msLibraryProxy.extendBookLoan(bookLoanId);
            LOGGER.debug("Mise à jour de l'emprunt id {}", bookLoanId);
            LOGGER.info("Mise à de l'emprunt id {} de l'utilisateur id {} par {} ({})", bookLoanId, bl.getUser().getId(), u.getEmail(), u.getRole());
        }

        if ("loanclosed".equalsIgnoreCase(action)) {
            msLibraryProxy.closeBookLoan(bookLoanId);
            LOGGER.debug("Cloture de l'emprunt id {}", bookLoanId);
            LOGGER.info("Cloture de l'emprunt id {} de l'utilisateur id {} par {} ({})", bookLoanId, bl.getUser().getId(), u.getEmail(), u.getRole());
        }

        List<BookLoanBean> bookLoanList = msLibraryProxy.getBookLoansByUserId(u.getId().toString());
        model.addAttribute("bookLoanList" , bookLoanList);

        return "redirect:/admin/profil#nav-bookloan";
    }

    @GetMapping("/admin/profil")
    public String adminProfil(Model model) {
        List<UserBean> usersList = msLibraryProxy.getUsers();
        model.addAttribute("usersList", usersList);
        LOGGER.info("Chargement de {} utilisateurs", usersList.size());

        List<BookLoanBean> bookLoanList = msLibraryProxy.getBookLoansList();
        model.addAttribute("bookLoanList", bookLoanList);
        LOGGER.info("Chargement de {} emprunts", bookLoanList.size());

        // return findPaginatedUsers(1, model);
        // return findPaginatedBookLoan(1, model);
        return "admin/profil";
    }

    @GetMapping("/admin/profil/user/page/{pageNumber}")
    public String findPaginatedUsers(@PathVariable (value= "PageNumber") int pageNumber, Model model) {
        int pageSize = 5;
        Page<UserBean> page = msLibraryProxy.getPaginatedUsers(pageNumber, pageSize);
        List<UserBean> userList = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("userList", userList);

        return "admin/profil";
    }


}

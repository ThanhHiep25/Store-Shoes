package com.example.shoestore.controller;

import com.example.shoestore.config.PasswordUtil;
import com.example.shoestore.model.Product;
import com.example.shoestore.model.User;
import com.example.shoestore.service.ProductService;
import com.example.shoestore.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {

    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public PageController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }

    @GetMapping("/products")
    public String productList() {
        return "product-list";
    }

    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        // Lấy thông tin sản phẩm theo id
        Product product = productService.getProductById(id);
        
        if (product != null) {
            // Đẩy dữ liệu sản phẩm vào model để sử dụng trên trang HTML
            model.addAttribute("product", product);
            return "product-detail";
        } else {
            // Nếu không tìm thấy sản phẩm, trả về trang lỗi hoặc chuyển hướng
            return "redirect:/";
        }
    }


    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String address,
            Model model) {

        try {
            // Loại bỏ khoảng trắng thừa từ mật khẩu
            password = password.trim();
            String hashedPassword = PasswordUtil.hashPassword(password);
            System.out.println("Hashed Password on Registration: " + hashedPassword);

            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPassword(hashedPassword);
            newUser.setPhone(phone);
            newUser.setAddress(address);
            newUser.setRole(User.Role.customer);

            userService.saveUser(newUser);

            model.addAttribute("message", "Registration successful!");
            return "login";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred during registration.");
            return "register";
        }
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {

        try {
            User user = userService.getUserByUsername(username);

            if (user != null) {
                String hashedPasswordFromDB = user.getPassword();
                boolean isPasswordValid = PasswordUtil.checkPassword(password, hashedPasswordFromDB);

                System.out.println("Password Entered: " + password);
                System.out.println("Hashed Password from DB: " + hashedPasswordFromDB);
                System.out.println("Password Valid: " + isPasswordValid);

                if (isPasswordValid) {
                    session.setAttribute("user", user);
                    return "redirect:/";
                } else {
                    model.addAttribute("error", "Invalid username or password");
                    return "login";
                }
            } else {
                model.addAttribute("error", "Invalid username or password");
                return "login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred during login.");
            return "login";
        }
    }


    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/admin/product-management")
    public String productManagement() {
        return "admin/product-management";
    }

    @GetMapping("/admin/order-management")
    public String orderManagement() {
        return "admin/order-management";
    }

    @GetMapping("/admin/user-management")
    public String userManagement() {
        return "admin/user-management";
    }
}

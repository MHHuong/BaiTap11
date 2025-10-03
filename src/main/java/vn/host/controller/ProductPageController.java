package vn.host.controller;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductPageController {

    // DTO cơ bản cho form (demo)
    @Data
    public static class ProductForm {
        private Long id;
        @NotBlank(message = "Tên không được trống")
        private String title;
        @DecimalMin(value = "0.0", inclusive = true, message = "Giá không âm")
        private Double price;
        @Min(value = 0, message = "Số lượng không âm")
        private Integer quantity;
        private String desc;
        private String imageUrl;
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new ProductForm());
        return "new_product"; // templates/new_product.html
    }

    @PostMapping
    public String create(@ModelAttribute("product") ProductForm form) {
        // TODO: lưu DB (nếu cần). Hiện demo chỉ redirect về trang chủ.
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // TODO: đọc từ DB. Demo tạo giả:
        ProductForm p = new ProductForm();
        p.setId(id);
        p.setTitle("Sản phẩm #" + id);
        p.setPrice(199000.0);
        p.setQuantity(5);
        p.setDesc("Mô tả ngắn...");
        p.setImageUrl("https://example.com/img.png");
        model.addAttribute("product", p);
        return "edit_product"; // templates/edit_product.html
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("product") ProductForm form) {
        // TODO: cập nhật DB
        return "redirect:/";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        // TODO: xóa DB
        return "redirect:/";
    }
}
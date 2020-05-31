package com.example.demo.controller;

import com.example.demo.pojo.Book;
import com.example.demo.pojo.User;
import com.example.demo.result.Result;
import com.example.demo.service.BookService;

import com.example.demo.service.UserService;
import com.example.demo.utils.StringUtils;
import com.sun.xml.internal.txw2.output.ResultFactory;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;
import com.sun.xml.internal.txw2.output.ResultFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    BookService bookService;
    UserService userService;

    @CrossOrigin
    @GetMapping("/api/books")
    public List<Book> list() throws Exception{
        return bookService.list();
    }

    @CrossOrigin
    @PostMapping("/api/books")
    public Book addOrUpdate(@RequestBody Book book) throws Exception{
        bookService.addOrUpdate(book);
        return book;
    }

    @CrossOrigin
    @PostMapping("/api/delete")
    public void delete(@RequestBody Book book) throws Exception{
        bookService.deleteById(book.getId());
    }

    @CrossOrigin
    @GetMapping("/api/categories/{cid}/books")
    public List<Book> listByCategory(@PathVariable("cid") int cid) throws Exception{
        // 如果 cid 不为 0，那么返回查询的特定类型书籍
        if (cid != 0)
            return bookService.listByCategory(cid);
        else{
            // 如果为 0 ，那么返回所有书籍
            return list();
        }
    }

    @CrossOrigin
    @GetMapping("/api/search")
    public List<Book> searchResult(@RequestParam("keywords") String keywords){
        if("".equals(keywords)) {
            return bookService.list();
        }else{
            return bookService.Search(keywords);
        }
    }

    @CrossOrigin
    @PostMapping("/api/covers")
    public String coverUpload(MultipartFile file) throws Exception{
        // 创建
        String folder = "D:/myProject/img";
        File imageFolder = new File(folder);
        // filename.format
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename().substring(file.getOriginalFilename().length() - 4));
        if(!f.getParentFile().exists()){
            f.getParentFile().mkdirs();
        }
        try{
            // 保存文件
            file.transferTo(f);
            String imgURL = "http://localhost:8014/api/file/" + f.getName();
            return imgURL;
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }

    @PostMapping("api/register")
    @ResponseBody
    public Result register(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);

        boolean exist = userService.isExist(username);
        if (exist) {
            String msg = "用户名已被占用";
            return com.example.demo.result.ResultFactory.buildFailResult(msg);
        }
        // 生产盐，默认 16 位
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // hash 次数
        int times = 2;
        String encodePassword = new SimpleHash("md5", password, salt, times).toString();
        // 存储用户信息
        user.setSalt(salt);
        user.setPassword(encodePassword);
        userService.add(user);

        return com.example.demo.result.ResultFactory.buildSuccessResult(user);
    }
}

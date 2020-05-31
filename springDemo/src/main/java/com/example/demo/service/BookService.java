package com.example.demo.service;

import com.example.demo.pojo.Book;
import com.example.demo.dao.BookDAO;
import com.example.demo.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookDAO bookDAO;
    @Autowired
    CategoryService categoryService;

    // 查看所有书籍
    public List<Book> list(){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return bookDAO.findAll(sort);
    }

    // 添加或者更新书籍
    public void addOrUpdate(Book book){
        bookDAO.save(book);
    }

    // 通过 id 删除书籍
    public void deleteById(int id){
        bookDAO.deleteById(id);
    }

    // 通过分类查出书籍
    public List<Book> listByCategory(int cid){
        Category c = categoryService.get(cid);
        return bookDAO.findAllByCategory(c);
    }

    // 根据题目或者作者进行模糊搜索
    public List<Book> Search(String keywords){
        return bookDAO.findAllByTitleLikeOrAuthorLike('%' + keywords + '%', '%' + keywords + '%');
    }
}

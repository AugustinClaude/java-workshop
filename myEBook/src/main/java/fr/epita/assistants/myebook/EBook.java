package fr.epita.assistants.myebook;

import java.util.ArrayList;
import java.util.List;

public class EBook implements IPaginated, IEditable {
    String name;
    List<String> pages;
    int currentPage;

    public EBook(String name) {
        this.name = name;
        pages = new ArrayList<>();
        pages.add("");
        currentPage = 0;
    }

    public String getName() {
        return name;
    }

    public Book print() {
        return new Book(name, new ArrayList<>(pages));
    }

    @Override
    public void openToPage(int page) {
        if (page < 0 || page >= pages.size())
            return;

        currentPage = page;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public int getPageCount() {
        return pages.size();
    }

    @Override
    public void writeCurrentPage(String pageText) {
        pages.set(currentPage, pageText);
    }

    @Override
    public void addPage() {
        pages.add(currentPage + 1, "");
    }

    @Override
    public void deletePage() {
        pages.remove(currentPage);
        if (pages.size() == 0)
            pages.add("");
    }
}

package fr.epita.assistants.myebook;

import java.util.List;

public class Book implements IReadable, IPaginated {
    String name;
    List<String> pages;
    int currentPage;

    protected Book(String name, List<String> pages) {
        this.name = name;
        this.pages = pages;
        currentPage = 0;
    }

    public String getName() {
        return name;
    }

    public EBook scan() {
        EBook ebook = new EBook(name);
        ebook.pages = pages;
        ebook.currentPage = currentPage;
        return ebook;
    }

    @Override
    public String readCurrentPage() {
        return pages.get(currentPage);
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
}

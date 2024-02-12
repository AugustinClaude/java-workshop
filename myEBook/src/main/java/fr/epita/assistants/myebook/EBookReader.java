package fr.epita.assistants.myebook;

import java.util.ArrayList;
import java.util.List;

public class EBookReader implements IReadable, IPaginated, IUpdatable {
    List<String> pages;
    int currentPage;
    double firmware;

    public EBookReader() {
        pages = null;
        currentPage = -1;
        firmware = 1.0;
    }

    public void openEbook(EBook ebook) {
        pages = ebook.pages;
        currentPage = ebook.currentPage;
    }

    @Override
    public String readCurrentPage() {
        if (pages == null)
            return null;
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
        if (pages == null)
            return -1;
        return currentPage;
    }

    @Override
    public int getPageCount() {
        if (pages == null)
            return -1;
        return pages.size();
    }

    @Override
    public double getVersion() {
        return firmware;
    }

    @Override
    public void update(double version) {
        if (version < firmware)
            return;
        firmware = version;
    }
}

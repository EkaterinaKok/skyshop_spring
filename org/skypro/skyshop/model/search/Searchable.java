package org.skypro.skyshop.model.search;

import java.util.Comparator;
import java.util.UUID;

public interface Searchable {

    String getSearchTerm();

    String getContentType();

    UUID getId();

    default String getStringRepresentation() {
        return this.getSearchTerm() + "\n" + this.getContentType();
    }

    public class SearchComporator implements Comparator<Searchable> {
        @Override
        public int compare(Searchable s1, Searchable s2) {
            int lengthComparison = Integer.compare(
                    s2.getSearchTerm().length(),
                    s1.getSearchTerm().length());

            if (lengthComparison != 0) {
                return lengthComparison;
            }
            return s1.getSearchTerm().compareToIgnoreCase(s2.getSearchTerm());
        }
    }


}

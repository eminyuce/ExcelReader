package com.excel.reader.model;

import com.excel.reader.entities.ExportImportAralik;
import com.excel.reader.util.ExcelConstants;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ExportImportAralikSpecification implements Specification<ExportImportAralik> {

    private static final long serialVersionUID = 1L;
    private final ExportImportAralikSearchParams exportImportAralikSearchParams;

    public ExportImportAralikSpecification(ExportImportAralikSearchParams exportImportAralikSearchParams) {
        this.exportImportAralikSearchParams = exportImportAralikSearchParams;
    }

    @Override
    public Predicate toPredicate(Root<ExportImportAralik> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        var criteriaList = exportImportAralikSearchParams.getCriteriaList();

        // Process criteria list for field-specific searching
        if (criteriaList != null && !criteriaList.isEmpty()) {
            for (SearchCriteria criteria : criteriaList) {
                String field = criteria.getField();
                String operator = criteria.getOperator();
                String value = criteria.getValue();

                switch (field) {
                    case "id":
                        predicates.add(applyOperator(cb, root.get("id"), operator, value));
                        break;
                    case "fileName":
                        predicates.add(applyOperator(cb, root.get("fileName"), operator, value));
                        break;
                    case "sheetName":
                        predicates.add(applyOperator(cb, root.get("sheetName"), operator, value));
                        break;
                    case "tcgbBolgeMudurluguKodu":
                        predicates.add(applyOperator(cb, root.get("tcgbBolgeMudurluguKodu"), operator, value));
                        break;
                    case "tcgbBolgeMudurluguAdi":
                        predicates.add(applyOperator(cb, root.get("tcgbBolgeMudurluguAdi"), operator, value));
                        break;
                    case "tcgbGumrukIdaresiKodu":
                        predicates.add(applyOperator(cb, root.get("tcgbGumrukIdaresiKodu"), operator, value));
                        break;
                    case "tcgbGumrukIdaresiAdi":
                        predicates.add(applyOperator(cb, root.get("tcgbGumrukIdaresiAdi"), operator, value));
                        break;
                    case "beyannameNo":
                        predicates.add(applyOperator(cb, root.get("beyannameNo"), operator, value));
                        break;
                    case "gumrukIstatistikTarihi":
                        predicates.add(applyOperator(cb, root.get("gumrukIstatistikTarihi"), operator, value));
                        break;
                    case "menseUlkeKodu":
                        predicates.add(applyOperator(cb, root.get("menseUlkeKodu"), operator, value));
                        break;
                    case "menseUlkeAdi":
                        predicates.add(applyOperator(cb, root.get("menseUlkeAdi"), operator, value));
                        break;
                    case "ticaretYapilanUlkeKodu":
                        predicates.add(applyOperator(cb, root.get("ticaretYapilanUlkeKodu"), operator, value));
                        break;
                    case "ticaretYapilanUlkeAdi":
                        predicates.add(applyOperator(cb, root.get("ticaretYapilanUlkeAdi"), operator, value));
                        break;
                    case "gtipKodu":
                        predicates.add(applyOperator(cb, root.get("gtipKodu"), operator, value));
                        break;
                    case "gtipAciklamasi":
                        predicates.add(applyOperator(cb, root.get("gtipAciklamasi"), operator, value));
                        break;
                    case "esyaTicariTanimi":
                        predicates.add(applyOperator(cb, root.get("esyaTicariTanimi"), operator, value));
                        break;
                    case "gondericiAdiUnvani":
                        predicates.add(applyOperator(cb, root.get("gondericiAdiUnvani"), operator, value));
                        break;
                    case "aliciAdiUnvani":
                        predicates.add(applyOperator(cb, root.get("aliciAdiUnvani"), operator, value));
                        break;
                    case "netAgirlik":
                        predicates.add(applyOperator(cb, root.get("netAgirlik"), operator, value));
                        break;
                    case "brutAgirlik":
                        predicates.add(applyOperator(cb, root.get("brutAgirlik"), operator, value));
                        break;
                    case "istatistikiKiymetUsdDegeri":
                        predicates.add(applyOperator(cb, root.get("istatistikiKiymetUsdDegeri"), operator, value));
                        break;
                    default:
                        break;
                }
            }
        }

        // Handle date range search
        if (exportImportAralikSearchParams.getFromDate() != null && exportImportAralikSearchParams.getToDate() != null) {
            // This is a date range search for gumrukIstatistikTarihi (Bordro Tarihi)
            Predicate dateRangePredicate = cb.between(
                    root.get("gumrukIstatistikTarihi"),
                    exportImportAralikSearchParams.getFromDate(),
                    exportImportAralikSearchParams.getToDate()
            );
            predicates.add(dateRangePredicate);
        }

//        // Handle ülke (country) filter if present
//        if (exportImportAralikSearchParams.getUlkeList() != null && !exportImportAralikSearchParams.getUlkeList().isEmpty()) {
//            Predicate ulkePredicate = cb.or(
//                    root.get("menseUlkeKodu").in(exportImportAralikSearchParams.getUlkeList()),
//                    root.get("ticaretYapilanUlkeKodu").in(exportImportAralikSearchParams.getUlkeList()),
//                    root.get("gidecegiUlkeKodu").in(exportImportAralikSearchParams.getUlkeList())
//            );
//            predicates.add(ulkePredicate);
//        }
//
//        // Handle GTIP filters if present
//        if (exportImportAralikSearchParams.getGtipList() != null && !exportImportAralikSearchParams.getGtipList().isEmpty()) {
//            Predicate gtipPredicate = root.get("gtipKodu").in(exportImportAralikSearchParams.getGtipList());
//            predicates.add(gtipPredicate);
//        }

        return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate applyOperator(CriteriaBuilder cb, Path<?> path, String operator, String value) {
        switch (operator) {
            case ExcelConstants.OPERATOR_EQUAL:
                return cb.equal(path, value);
            case ExcelConstants.OPERATOR_LIKE:
                return cb.like(path.as(String.class), "%" + value + "%");
            case ExcelConstants.OPERATOR_NOT_EQUAL:
                return cb.notEqual(path, value);
            case ExcelConstants.OPERATOR_GREATER_THAN:
                try {
                    return cb.greaterThan(path.as(Double.class), Double.parseDouble(value));
                } catch (NumberFormatException e) {
                    return cb.greaterThan(path.as(String.class), value);
                }
            case ExcelConstants.OPERATOR_LESS_THAN:
                try {
                    return cb.lessThan(path.as(Double.class), Double.parseDouble(value));
                } catch (NumberFormatException e) {
                    return cb.lessThan(path.as(String.class), value);
                }
            case ExcelConstants.OPERATOR_GREATER_THAN_OR_EQUAL:
                try {
                    return cb.greaterThanOrEqualTo(path.as(Double.class), Double.parseDouble(value));
                } catch (NumberFormatException e) {
                    return cb.greaterThanOrEqualTo(path.as(String.class), value);
                }
            case ExcelConstants.OPERATOR_LESS_THAN_OR_EQUAL:
                try {
                    return cb.lessThanOrEqualTo(path.as(Double.class), Double.parseDouble(value));
                } catch (NumberFormatException e) {
                    return cb.lessThanOrEqualTo(path.as(String.class), value);
                }
            default:
                return cb.conjunction();
        }
    }
}
package com.excel.reader.model;

import com.excel.reader.entities.ExportImportOther;
import com.excel.reader.util.ExcelConstants;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ExportImportOtherSpecification implements Specification<ExportImportOther> {

    private static final long serialVersionUID = 1L;
    private final ExportImportOtherSearchParams exportImportOtherSearchParams;

    public ExportImportOtherSpecification(ExportImportOtherSearchParams exportImportOtherSearchParams) {
        this.exportImportOtherSearchParams = exportImportOtherSearchParams;
    }

    @Override
    public Predicate toPredicate(Root<ExportImportOther> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        var criteriaList = exportImportOtherSearchParams.getCriteriaList();

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
                    case "tcgbGumrukIdaresiKodu":
                        predicates.add(applyOperator(cb, root.get("tcgbGumrukIdaresiKodu"), operator, value));
                        break;
                    case "tcgbGumrukIdaresiAdi":
                        predicates.add(applyOperator(cb, root.get("tcgbGumrukIdaresiAdi"), operator, value));
                        break;
                    case "tcgbTescilNo":
                        predicates.add(applyOperator(cb, root.get("tcgbTescilNo"), operator, value));
                        break;
                    case "tcgbTescilTarihi":
                        predicates.add(applyOperator(cb, root.get("tcgbTescilTarihi"), operator, value));
                        break;
                    case "tcgbKapanisTarihi":
                        predicates.add(applyOperator(cb, root.get("tcgbKapanisTarihi"), operator, value));
                        break;
                    case "gondericiAliciVergiNo":
                        predicates.add(applyOperator(cb, root.get("gondericiAliciVergiNo"), operator, value));
                        break;
                    case "gondericiAliciAdi":
                        predicates.add(applyOperator(cb, root.get("gondericiAliciAdi"), operator, value));
                        break;
                    case "aliciAdi":
                        predicates.add(applyOperator(cb, root.get("aliciAdi"), operator, value));
                        break;
                    case "gidecegiUlkeKodu":
                        predicates.add(applyOperator(cb, root.get("gidecegiUlkeKodu"), operator, value));
                        break;
                    case "gidecegiUlkeAdi":
                        predicates.add(applyOperator(cb, root.get("gidecegiUlkeAdi"), operator, value));
                        break;
                    case "menseUlkeKodu":
                        predicates.add(applyOperator(cb, root.get("menseUlkeKodu"), operator, value));
                        break;
                    case "menseUlkeAdi":
                        predicates.add(applyOperator(cb, root.get("menseUlkeAdi"), operator, value));
                        break;
                    case "teslimSekliKodu":
                        predicates.add(applyOperator(cb, root.get("teslimSekliKodu"), operator, value));
                        break;
                    case "kalemSiraNo":
                        predicates.add(applyOperator(cb, root.get("kalemSiraNo"), operator, value));
                        break;
                    case "kalemRejimKodu":
                        predicates.add(applyOperator(cb, root.get("kalemRejimKodu"), operator, value));
                        break;
                    case "kalemRejimAciklamasi":
                        predicates.add(applyOperator(cb, root.get("kalemRejimAciklamasi"), operator, value));
                        break;
                    case "kalemRejimAciklmasi":
                        predicates.add(applyOperator(cb, root.get("kalemRejimAciklmasi"), operator, value));
                        break;
                    case "gtipKodu":
                        predicates.add(applyOperator(cb, root.get("gtipKodu"), operator, value));
                        break;
                    case "gtipAciklamasi":
                        predicates.add(applyOperator(cb, root.get("gtipAciklamasi"), operator, value));
                        break;
                    case "ticariTanimi":
                        predicates.add(applyOperator(cb, root.get("ticariTanimi"), operator, value));
                        break;
                    case "tcgbStatuAciklamasi":
                        predicates.add(applyOperator(cb, root.get("tcgbStatuAciklamasi"), operator, value));
                        break;
                    case "faturaTutari":
                        predicates.add(applyOperator(cb, root.get("faturaTutari"), operator, value));
                        break;
                    case "faturaTutariDovizTuruKodu":
                        predicates.add(applyOperator(cb, root.get("faturaTutariDovizTuruKodu"), operator, value));
                        break;
                    case "olcuEsyaMiktari":
                        predicates.add(applyOperator(cb, root.get("olcuEsyaMiktari"), operator, value));
                        break;
                    case "olcuBirimiAciklamasi":
                        predicates.add(applyOperator(cb, root.get("olcuBirimiAciklamasi"), operator, value));
                        break;
                    case "netAgirlikKg":
                        predicates.add(applyOperator(cb, root.get("netAgirlikKg"), operator, value));
                        break;
                    case "hesaplanmisKalemKiymetiUsdDegeri":
                        predicates.add(applyOperator(cb, root.get("hesaplanmisKalemKiymetiUsdDegeri"), operator, value));
                        break;
                    case "istatistikiKiymetUsdDegeri":
                        predicates.add(applyOperator(cb, root.get("istatistikiKiymetUsdDegeri"), operator, value));
                        break;
                    default:
                        break;
                }
            }
        }

        // Handle date range search (if applicable)
        if (exportImportOtherSearchParams.getFromDate() != null && exportImportOtherSearchParams.getToDate() != null) {
            Predicate dateRangePredicate = cb.between(
                    root.get("tcgbTescilTarihi"),
                    exportImportOtherSearchParams.getFromDate(),
                    exportImportOtherSearchParams.getToDate()
            );
            predicates.add(dateRangePredicate);
        }

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

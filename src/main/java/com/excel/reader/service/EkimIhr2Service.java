package com.excel.reader.service;

import com.excel.reader.entities.EkimIhr2;
import com.excel.reader.repo.EkimIhr2Repository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class EkimIhr2Service {

    private final EkimIhr2Repository ekimIhr2Repository;
    private static final int THREAD_COUNT = 8; // Adjust based on your DB connection pool
    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    public EkimIhr2Service(EkimIhr2Repository ekimIhr2Repository) {
        this.ekimIhr2Repository = ekimIhr2Repository;
    }

    @Transactional
    public void saveEkimIhr2(EkimIhr2 entity) {
        ekimIhr2Repository.saveEkimIhr2(
                entity.getRowNumber(),
                entity.getTcgbGumrukIdaresiKodu(),
                entity.getTcgbGumrukIdaresiAdi(),
                entity.getTcgbTescilNo(),
                entity.getTcgbTescilTarihi(),
                entity.getTcgbKapanisTarihi(),
                entity.getGondericiAliciVergiNo(),
                entity.getGondericiAliciAdi(),
                entity.getAliciAdi(),
                entity.getGidecegiUlke17Kodu(),
                entity.getGidecegiUlke17Adi(),
                entity.getMenseUlkeKodu(),
                entity.getMenseUlkeAdi(),
                entity.getTeslimSekliKodu(),
                entity.getKalemSiraNo(),
                entity.getKalemRejimKodu(),
                entity.getKalemRejimAciklamasi(),
                entity.getGtipKodu(),
                entity.getGtipAciklamasi(),
                entity.getTicariTanimi31(),
                entity.getTcgbStatuAciklamasi(),
                entity.getFaturaTutari(),
                entity.getFaturaTutariDovizTuruKodu(),
                entity.getOlcuEsyaMiktari(),
                entity.getOlcuBirimiAciklamasi(),
                entity.getNetAgirlikKg(),
                entity.getHesaplanmisKalemKiymetiUsdDegeri(),
                entity.getIstatistikiKiymetUsdDegeri()
        );
    }

    @Transactional
    public void saveAllEkimIhr2List(List<EkimIhr2> batch) {
        // Split the batch into smaller chunks for parallel processing
        int chunkSize = Math.max(1, batch.size() / THREAD_COUNT);
        List<List<EkimIhr2>> chunks = partitionList(batch, chunkSize);

        // Process chunks in parallel
        List<CompletableFuture<Void>> futures = chunks.stream()
                .map(chunk -> CompletableFuture.runAsync(() -> {
                    try {
                        for (EkimIhr2 item : chunk) {
                            saveEkimIhr2(item);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to process chunk: " + e.getMessage(), e);
                    }
                }, executorService))
                .collect(Collectors.toList());

        // Wait for all chunks to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .exceptionally(throwable -> {
                    System.err.println("Error in batch processing: " + throwable.getMessage());
                    return null;
                })
                .join();
    }

    // Helper method to partition the list into chunks
    private List<List<EkimIhr2>> partitionList(List<EkimIhr2> list, int chunkSize) {
        List<List<EkimIhr2>> partitions = new ArrayList<>();
        for (int i = 0; i < list.size(); i += chunkSize) {
            partitions.add(list.subList(i, Math.min(i + chunkSize, list.size())));
        }
        return partitions;
    }

    // Cleanup method - call this when application shuts down
    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    @Async
    @Transactional
    public void saveAllEkimIhr2ListAsync(List<EkimIhr2> batch) {
        for (EkimIhr2 item : batch) {
            saveEkimIhr2Async(item);
        }
    }
    @Async
    @Transactional
    public void saveEkimIhr2Async(EkimIhr2 entity) {
        saveEkimIhr2(entity); // Delegate to the synchronous method
    }

    public Integer getLastRowNumber() {
        Integer lastRowNumber = ekimIhr2Repository.findLastRowNumber();
        return lastRowNumber;
    }
}
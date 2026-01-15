-- Migration: add deposit column to HoaDon
-- Run this script against the application's database to add support for deposits
IF COL_LENGTH('dbo.HoaDon', 'TienTraTruoc') IS NULL
BEGIN
    ALTER TABLE HoaDon ADD TienTraTruoc BIGINT DEFAULT 0;
END

INSERT INTO car (image, model, popularity, seats, transmission, fuel, price, about)

    VALUES
        ('https://driveshare.com/files/car_images/15200/1725736643_php66dca6c3f3025.jpg.full.jpg?t=1725736671', '1967 Ford Mustang', 59, 4, 'Automatic', '87/91 Octane', 300, 'It runs great. I drive it a few times a week so it has always been kept in good working order and stored over the winter indoors.'),
        ('https://driveshare.com/files/car_images/8550/1682265181_php6445545d87fb2.jpg.full.jpg?t=1682360775', '1936 Ford 68 Deluxe Touring', 45, 4, 'Automatic', '87/92 Octane', 100, 'This beautiful vintage car is almost 90 years old, but looks and drives like it''s brand new! When you ride in this car, it''s like stepping back in time of the days of old.'),
        ('https://driveshare.com/files/car_images/5500/1643489643_phpil1lpX.jpg.full.jpg?t=1644003092', '1941 Cadillac Series 62', 33, 4, 'Manual', '87 Octane', 725, 'I had the opportunity to take possession of this one 12 years ago and completed a two year frame on restoration. This all original prewar car is number 1562 of 3100 produced.'),
        ('https://driveshare.com/files/car_images/10900/1714329698_php662e98620a942.jpg.full.jpg?t=1725413386', '1972 Ford Mustang Convertible', 45, 4, 'Automatic', '87 Octane', 375, 'This 1972 Ford Mustang Convertible is the perfect blend of classic style and comfort. It`s ideal for weekend drives or special occasions.'),
        ('https://driveshare.com/files/car_images/1350/1692102007_php64db6d773b85f.jpg.full.jpg?t=1692214944', '1960 Mercedes-Benz 190SL', 66, 2, 'Manual', '97 Octane', 600, 'This stunning 1960 Mercedes-Benz 190SL offers a nostalgic driving experience. Perfect for special events or a leisurely drive, it is well-maintained and delivers a smooth, timeless ride.'),
        ('https://driveshare.com/files/car_images/9050/1722991542_php66b2c3b603ead.jpg.full.jpg?t=1728305930', '1951 Pontiac Eight', 62, 6, 'Manual', '91 Octane', 500, 'Pontiac started in 1926 and celebrated its silver anniversary in 1951. This car is a time capsule. It does have recent paint and is in superb condition.'),
        ('https://driveshare.com/files/car_images/9100/1676995584_php63f4ec0019991.jpg.full.jpg?t=1676997508', '1970 Ford Mustang', 58, 4, 'Automatic', '87 Octane', 420, 'Well she’s a 52 yr old machine, so she shows a bit of patina here and there but look at all the heads turn looking at her. And listen to her.'),
        ('https://driveshare.com/files/car_images/9750/1685333523_php64742613029e8.jpg.full.jpg?t=1685919413', '1957 Porsche speedster', 47, 2, 'Manual', '95 Octane', 370, 'Amazing Porsche 356 Speedster replica of James Deans first race car! Perfect for photo shoots, weddings, or a coastal cruise.'),
        ('https://driveshare.com/files/car_images/8600/1655147108_php62a78a64e6891.jpg.full.jpg?t=1655147332', '1957 Chevrolet Bel Air', 23, 6, 'Automatic', '87 Octane', 400, 'This car is the perfect combination of the incredible and timeless styling of a 1957 Chevrolet Bel Air Convertible and a modern driving chassis of a 1980s Cadillac Eldorado.'),
        ('https://driveshare.com/files/car_images/4100/1685305602_php6473b902ae268.jpg.full.jpg?t=1685714457', '1978 Volkswagen Bus', 45, 5, 'Manual', '95 Octane', 200, 'The 1978 Volkswagen Bus offers a nostalgic ride with its iconic design and spacious interior, perfect for road trips or events. This classic van ensures a unique and memorable driving experience.'),
        ('https://driveshare.com/files/car_images/6200/1615398284_phpI5wVk8.jpg.full.jpg?t=1615471364', '1954 Mercedes-Benz 300B', 80, 4, 'Manual', '95 Octane', 700, 'The 1954 Mercedes-Benz 300B combines timeless elegance with powerful performance, making it perfect for classic car enthusiasts. This iconic vehicle delivers a refined driving experience for any special occasion.'),
        ('https://driveshare.com/files/car_images/8100/1644610696_php7Dy9Fo.jpg.full.jpg?t=1644610708', '1951 Ford Country Squire', 48, 8, 'Automatic', '95 Octane', 500, 'The 1951 Ford Country Squire is a classic wagon that embodies vintage charm and spaciousness, making it perfect for family adventures.')
           AS car_row

ON DUPLICATE KEY UPDATE
                     image = car_row.image,
                     model = car_row.model,
                     popularity = car_row.popularity,
                     seats = car_row.seats,
                     transmission = car_row.transmission,
                     fuel = car_row.fuel,
                     price = car_row.price,
                     about = car_row.about;

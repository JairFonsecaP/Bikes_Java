USE `bikes`;


INSERT INTO Category (name, image) VALUES('Mountain bike' , 'https://upload.wikimedia.org/wikipedia/commons/1/1f/Norco_Range.jpg');
INSERT INTO Category (name, image) VALUES('Triathlon bike' , 'https://cdn.mos.cms.futurecdn.net/LeVCbSzJxqSjVyo47aynJR-1280-80.jpg');


INSERT INTO Brand (name, image) VALUES('Trek' , 'https://theloamwolf.com/wp-content/uploads/2020/04/trek-logo.jpg');
INSERT INTO Brand (name, image) VALUES('Specialized' , 'https://logovtor.com/wp-content/uploads/2020/05/specialized-bicycle-components-logo-vector.png');

INSERT INTO Product (Name, Desciption, Price, Stock, Image, Brand_Id, Category_Id) VALUES('Top Fuel 9.9 XX1 AXS' , "Top Fuel 9.9 XX1 AXS pulls out all the stops. This full suspension carbon mountain bike is built with the latest in fully wireless components like a SRAM XX1 Eagle AXS drivetrain and RockShox Reverb AXS dropper. It's also spec'd with high-end RockShox suspension and lots of carbon—including the wheels, bars, and shift levers.", 11499.99, 12, 'https://trek.scene7.com/is/image/TrekBicycleProducts/TopFuel99XX1AXS_22_35326_B_Portrait', 1, 1);
INSERT INTO Product (Name, Desciption, Price, Stock, Image, Brand_Id, Category_Id) VALUES('Epic Expert' , "When speed comes calling, the Epic Expert doesn’t just answer, it yells back, ‘Catch me if you can!’Featuring the most efficient suspension platform ever to grace a World Cup start line, the Epic Expert pairs 100-millimeters of Brain-controlled travel front and rear for a package that was born to erase seconds from the clock…", 8899.00, 25, 'https://assets.specialized.com/i/specialized/97620-32_EPIC-EXPERT-CARB-SPCTFLR_HERO?bg=rgb(241,241,241)', 2, 1);
INSERT INTO Product (Name, Desciption, Price, Stock, Image, Brand_Id, Category_Id) VALUES('Speed Concept SLR 9 eTap' , "Speed Concept SLR 9 eTap is an aerodynamic carbon triathlon bike engineered for incredible speed and seamless integration. Every bit of this bike—from the light aero frame to ride-smoothing IsoSpeed to cutting-edge fuel and hydration systems—has been painstakingly designed to help you beat your PR again, and again, and again.", 13999.99, 3, 'https://trek.scene7.com/is/image/TrekBicycleProducts/SpeedConceptSLR9eTap_22_35756_B_Portrait', 1, 2);
INSERT INTO Product (Name, Desciption, Price, Stock, Image, Brand_Id, Category_Id) VALUES('S-Works Shiv Disc' , "We've never been ones for dogmatic rules. And with a little defiance of the UCI's rule book, the Shiv sets the new standard for speed—again. It keeps its aero, fuel, and fit focus, but does so in an entirely new and novel way. Yes, it has disc brakes. Yes, it's easy to travel with…", 18549.00, 20, 'https://assets.specialized.com/i/specialized/97419-01_SHIV-SW-DISC-DI2-CARB-SILHLG_HERO', 2, 2);

INSERT INTO `bikes`.`admins`(`Name`,`Username`,`Password`,`Salt`)VALUES("Dara","dara","krvCQnZn6NXH0pZkce8RWqcHDRKrsUEM9Bb0g5iUXBU=","20RE1JsUEcpRcR9EEm39Q5qpx8Mggz");
INSERT INTO `bikes`.`admins`(`Name`,`Username`,`Password`,`Salt`)VALUES("Ximena","ximena","C3Q5pL+BTj7LFRBuSg8JyTcMDMmw/3bkFsc4yu2MHhE=","1D92mI2ejoBcrn6sITkpNiUzsMFhYL");
INSERT INTO `bikes`.`admins`(`Name`,`Username`,`Password`,`Salt`)VALUES("Jair","jair","0RKHp8tXxv2cng93H6vmo9luNhrHgH2b84ePv8zLn5o=","x7ZIxnM1XkfG9mrl4lqYrqlGYBlIbb");


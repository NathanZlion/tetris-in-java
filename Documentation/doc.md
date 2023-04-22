

# Blocks
## Types
### Shapes
---
1. **I block** - This block consists of four connected squares in a straight line.
    - Rotation 1:
        ```
        |X| | | |
        |X| | | |
        |X| | | |
        |X| | | |
        ```
    - Rotation 2:
        ```
        | | | | |
        | | | | |
        | | | | |
        |X|X|X|X|
        ```
---
2. **J block** - This block consists of three connected squares in the shape of an "L".
   - Rotation 1:
        ```
       |X| | |
       |X|X|X|
       | | | |

   - Rotation 2:
        ```
        | |X|X|
        | |X| |
        | |X| |

    - Rotation 3:
        ```
        | | | |
        |X|X|X|
        | | |X|
        ```
    - Rotation 4:
        ```
        | |X| |
        | |X| |
        |X|X| |
        ```
---
3. **L block** - This block consists of three connected squares in the shape of an "L" facing the opposite direction of the J block.
   - Rotation 1:
        ```
        | | |X|
        |X|X|X|
        | | | |
        ```
    - Rotation 2:
        ```
        | |X| |
        | |X| |
        | |X|X|
        ```
    - Rotation 3:
        ```
        | | | |
        |X|X|X|
        |X| | |
        ```
    - Rotation 4:
        ```
        |X|X| |
        | |X| |
        | |X| |
        ```
---
4. **O block** - This block consists of four connected squares in a 2x2 square.
   - Rotation 1:
        ```
        |X|X|
        |X|X|
        ```
---
5. **S block** - This block consists of four connected squares in the shape of a "Z".
    - Rotation 1:
        ```
        | |X|X|
        |X|X| |
        | | | |
        ```
    - Rotation 2:
        ```
        | |X| |
        | |X|X|
        | | |X|
        ```
---
6. **T block** - This block consists of three connected squares in the shape of a "T".
    - Rotation 1:
        ```
        | |X| |
        |X|X|X|
        | | | |
        ```
    - Rotation 2:
        ```
        | |X| |
        | |X|X|
        | |X| |
        ```
    - Rotation 3:
        ```
        | | | |
        |X|X|X|
        | |X| |
        ```
    - Rotation 4:
        ```
        | |X| |
        |X|X| |
        | |X| |
        ```
---
7. **Z block** - This block consists of four connected squares in the shape of a reversed "Z".
   - Rotation 1:
        ```
        |X|X| |
        | |X|X|
        | | | |
        ```
   - Rotation 2:
        ```
        | | |X|
        | |X|X|
        | |X| |
        ```

---
### Colors
* **I block:** `new Color(0, 255, 255); // cyan`
* **J block:** ` new Color(0, 0, 255); // blue`
* **L block:** ` new Color(255, 165, 0); // orange`
* **O block:** ` new Color(255, 255, 0); // yellow`
* **S block:** ` new Color(0, 255, 0); // green`
* **T block:** ` new Color(128, 0, 128); // purple`
* **Z block:** ` new Color(255, 0, 0); // red`


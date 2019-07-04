all: cinterop kotlin-native

cinterop:
	@if [ ! -d build ]; then mkdir build; fi
	@cinterop -o build/png \
		-def png.def \
		-compilerOpts -I/usr/local/include

kotlin-native:
	@if [ ! -d build ]; then mkdir build; fi
	@kotlinc-native -o build/main \
		Main.kt \
		-l build/png.klib \
		-linker-options /usr/local/lib/libpng.dylib

run:
	@build/main.kexe

clean:
	@if [ -d build ]; then rm -rf build; fi

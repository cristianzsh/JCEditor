/* The following code was generated by JFlex 1.4.1 on 12/03/16 15:06 */

package com.cristian;

import java.io.*;
import javax.swing.text.Segment;

import org.fife.ui.rsyntaxtextarea.AbstractJFlexTokenMaker;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMaker;
import org.fife.ui.rsyntaxtextarea.TokenImpl;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 12/03/16 15:06 from the specification file
 * <tt>/home/cristian/Documentos/PotigolTokenMaker.flex</tt>
 */
public class PotigolTokenMaker extends AbstractJFlexTokenMaker {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int LONG_STRING_2 = 1;
  public static final int LONG_STRING_1 = 1;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\34\1\12\1\0\1\34\1\32\22\0\1\34\1\32\1\11"+
    "\1\33\1\0\2\32\1\10\1\66\1\66\1\70\1\27\1\70\1\27"+
    "\1\24\1\70\1\16\1\65\6\21\2\3\1\73\1\75\1\71\1\67"+
    "\1\72\1\32\1\74\4\23\1\26\1\23\2\2\1\63\1\31\1\2"+
    "\1\15\3\2\1\62\1\2\1\6\2\2\1\7\2\2\1\20\2\2"+
    "\1\66\1\13\1\66\1\70\1\1\1\0\1\40\1\22\1\36\1\52"+
    "\1\25\1\50\1\56\1\51\1\41\1\30\1\2\1\14\1\42\1\44"+
    "\1\47\1\43\1\55\1\4\1\35\1\45\1\5\1\37\1\2\1\17"+
    "\1\2\1\64\1\66\1\32\1\66\1\32\142\0\1\61\1\0\1\46"+
    "\3\0\1\54\1\0\1\53\11\0\1\57\6\0\1\60\uff05\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\2\1\3\4\2\2\4\1\5\1\2"+
    "\1\3\1\6\1\7\1\6\1\2\1\10\1\11\15\2"+
    "\1\1\1\2\1\12\3\6\1\2\2\0\1\13\1\3"+
    "\1\14\1\13\1\14\3\2\1\4\2\0\2\2\1\13"+
    "\1\3\1\13\1\2\1\7\2\2\1\7\22\2\1\0"+
    "\3\2\1\15\4\2\1\7\2\2\4\0\2\14\1\0"+
    "\5\2\1\15\1\3\4\2\1\15\7\2\1\7\20\2"+
    "\1\0\2\2\1\7\1\2\3\0\1\14\12\2\1\0"+
    "\16\2\1\0\1\2\1\0\10\2\3\0\5\2\2\7"+
    "\1\0\1\2\1\0\10\2\1\0\1\2\1\0\1\15"+
    "\1\2\1\0\2\2\1\0\1\15\5\2\1\0\1\2"+
    "\2\0\1\2\1\0\2\2\1\0\3\2\1\0\1\2"+
    "\1\0\1\2\1\0\3\2\1\0\6\2\1\15";

  private static int [] zzUnpackAction() {
    int [] result = new int[245];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\76\0\174\0\272\0\370\0\u0136\0\u0174\0\u01b2"+
    "\0\u01f0\0\u022e\0\u026c\0\174\0\u02aa\0\u02e8\0\u0326\0\u0364"+
    "\0\174\0\u03a2\0\u03e0\0\u041e\0\u045c\0\u049a\0\u04d8\0\u0516"+
    "\0\u0554\0\u0592\0\u05d0\0\u060e\0\u064c\0\u068a\0\u06c8\0\u0706"+
    "\0\u0744\0\u0782\0\u07c0\0\174\0\u07fe\0\u083c\0\u087a\0\174"+
    "\0\u08b8\0\u08f6\0\u0934\0\u0934\0\u0972\0\u09b0\0\u0934\0\u09ee"+
    "\0\u0a2c\0\u0a6a\0\174\0\u0aa8\0\u0ae6\0\u0b24\0\u0b62\0\u0ba0"+
    "\0\u0bde\0\u0c1c\0\u0c5a\0\272\0\u0c98\0\u0cd6\0\u0d14\0\u0d52"+
    "\0\u0d90\0\u0dce\0\u0e0c\0\u0e4a\0\u0e88\0\u0ec6\0\u0f04\0\u0f42"+
    "\0\u0f80\0\u0fbe\0\u0ffc\0\u103a\0\u1078\0\u10b6\0\u10f4\0\u1132"+
    "\0\u1170\0\u11ae\0\u11ec\0\u122a\0\u1268\0\272\0\u12a6\0\u12e4"+
    "\0\u1322\0\u1360\0\u139e\0\u13dc\0\u141a\0\u1458\0\u1496\0\u14d4"+
    "\0\u1512\0\174\0\u1550\0\u158e\0\u15cc\0\u160a\0\u1648\0\u1686"+
    "\0\u16c4\0\u1702\0\u1740\0\u177e\0\u17bc\0\u17fa\0\u1838\0\u1876"+
    "\0\u18b4\0\u18f2\0\u1930\0\u196e\0\u19ac\0\u19ea\0\u1a28\0\174"+
    "\0\u1a66\0\u1aa4\0\u1ae2\0\u1b20\0\u1b5e\0\u1b9c\0\u1bda\0\u1c18"+
    "\0\u1c56\0\u1c94\0\u1cd2\0\u1d10\0\u1d4e\0\u1d8c\0\u1dca\0\u1e08"+
    "\0\u1e46\0\u1e84\0\u1ec2\0\u1f00\0\u1f3e\0\u1f7c\0\u1fba\0\u1ff8"+
    "\0\u2036\0\u2074\0\u20b2\0\u20f0\0\u212e\0\u216c\0\u21aa\0\u21e8"+
    "\0\u2226\0\u2264\0\u22a2\0\u22e0\0\u231e\0\u235c\0\u239a\0\u23d8"+
    "\0\u2416\0\u2454\0\u2492\0\u24d0\0\u250e\0\u254c\0\u258a\0\u25c8"+
    "\0\u2606\0\u2644\0\u2682\0\u26c0\0\u26fe\0\u273c\0\u277a\0\u27b8"+
    "\0\u27f6\0\u2834\0\u2872\0\u28b0\0\u28ee\0\u292c\0\u296a\0\u29a8"+
    "\0\u29e6\0\u2a24\0\u2a62\0\u2aa0\0\u2ade\0\u2b1c\0\u2b5a\0\u2b98"+
    "\0\u2bd6\0\u2c14\0\u2c52\0\u2c90\0\u2cce\0\u2d0c\0\u2d4a\0\u2d88"+
    "\0\u2dc6\0\u2e04\0\u2e42\0\u2e80\0\u2ebe\0\u2efc\0\u2f3a\0\u2f78"+
    "\0\u2fb6\0\u2ff4\0\u3032\0\174\0\u3070\0\u30ae\0\u30ec\0\u312a"+
    "\0\u3168\0\u31a6\0\u31e4\0\u3222\0\u3260\0\u329e\0\u32dc\0\u331a"+
    "\0\u3358\0\u3396\0\u33d4\0\u3412\0\u3450\0\u348e\0\u34cc\0\u350a"+
    "\0\u3548\0\u3586\0\u35c4\0\u3602\0\u3640\0\u367e\0\u36bc\0\u36fa"+
    "\0\u3738\0\u3776\0\u37b4\0\u37f2\0\u0ec6";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[245];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\2\4\1\5\1\6\1\7\1\10\1\11\1\12"+
    "\1\13\1\14\1\3\1\15\1\4\1\16\2\4\1\5"+
    "\2\4\1\17\1\20\1\4\1\21\1\22\1\4\1\3"+
    "\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32"+
    "\1\33\1\34\1\35\1\3\1\36\1\37\1\4\1\40"+
    "\2\3\1\4\1\41\1\3\1\42\1\3\1\43\2\4"+
    "\1\5\1\44\1\45\1\21\1\45\1\46\1\47\2\50"+
    "\37\0\1\51\10\0\1\52\124\0\7\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\11\4\1\0\4\4"+
    "\2\0\2\4\3\0\4\4\10\0\3\53\1\5\4\53"+
    "\3\0\1\53\2\54\1\5\2\53\1\5\2\53\1\55"+
    "\2\56\1\0\2\57\1\0\1\53\1\0\30\53\1\5"+
    "\6\0\1\53\2\0\7\4\1\12\1\13\2\0\10\4"+
    "\1\0\1\60\1\4\1\0\2\4\3\0\3\4\1\61"+
    "\5\4\1\0\4\4\2\0\2\4\3\0\4\4\11\0"+
    "\3\4\1\10\1\4\1\10\1\4\1\12\1\13\2\0"+
    "\1\62\7\4\1\0\2\4\1\0\2\4\3\0\11\4"+
    "\1\0\4\4\2\0\2\4\3\0\4\4\11\0\7\4"+
    "\1\12\1\13\2\0\10\4\1\0\2\4\1\0\2\4"+
    "\3\0\11\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\3\4\1\10\1\4\1\10\1\4\1\12\1\13"+
    "\2\0\10\4\1\0\2\4\1\0\2\4\3\0\11\4"+
    "\1\0\4\4\2\0\2\4\3\0\4\4\10\0\10\12"+
    "\1\63\1\12\1\0\1\64\62\12\11\13\1\63\1\0"+
    "\1\65\62\13\1\0\7\4\4\0\10\4\1\0\1\66"+
    "\1\4\1\0\2\4\3\0\11\4\1\0\1\67\3\4"+
    "\2\0\2\4\3\0\4\4\10\0\3\53\1\70\4\53"+
    "\3\0\1\53\2\54\1\71\2\72\1\71\2\53\1\55"+
    "\2\56\1\0\2\57\1\0\1\53\1\0\30\53\1\71"+
    "\6\0\1\53\4\0\1\55\12\0\1\55\2\0\1\55"+
    "\43\0\1\55\11\0\7\4\4\0\10\4\1\0\2\4"+
    "\1\0\2\4\3\0\1\73\4\4\1\74\1\4\1\75"+
    "\1\4\1\0\4\4\2\0\2\4\3\0\4\4\11\0"+
    "\4\4\1\76\2\4\4\0\10\4\1\0\2\4\1\0"+
    "\2\4\3\0\11\4\1\0\4\4\2\0\2\4\3\0"+
    "\4\4\10\0\12\23\1\0\63\23\34\0\1\24\42\0"+
    "\7\4\4\0\10\4\1\0\1\77\1\4\1\0\2\4"+
    "\3\0\11\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\7\4\4\0\10\4\1\0\2\4\1\0\2\4"+
    "\3\0\3\4\1\100\5\4\1\0\1\101\3\4\2\0"+
    "\2\4\3\0\4\4\11\0\7\4\4\0\10\4\1\0"+
    "\1\102\1\4\1\0\2\4\3\0\3\4\1\103\5\4"+
    "\1\0\4\4\2\0\2\4\3\0\4\4\11\0\3\4"+
    "\1\104\3\4\4\0\1\105\5\4\1\106\1\4\1\0"+
    "\2\4\1\0\2\4\3\0\10\4\1\107\1\0\4\4"+
    "\2\0\2\4\3\0\4\4\11\0\7\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\5\4\1\110\1\4"+
    "\1\111\1\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\4\4\1\112\2\4\4\0\10\4\1\0\2\4"+
    "\1\0\2\4\3\0\3\4\1\113\1\114\4\4\1\0"+
    "\1\115\3\4\2\0\2\4\3\0\4\4\11\0\7\4"+
    "\4\0\10\4\1\0\1\116\1\4\1\0\2\4\3\0"+
    "\3\4\1\117\5\4\1\0\1\120\3\4\2\0\2\4"+
    "\3\0\4\4\11\0\7\4\4\0\10\4\1\0\2\4"+
    "\1\0\2\4\3\0\3\4\1\121\5\4\1\122\4\4"+
    "\2\0\2\4\3\0\4\4\11\0\7\4\4\0\10\4"+
    "\1\0\1\123\1\4\1\0\2\4\3\0\3\4\1\124"+
    "\1\125\4\4\1\0\4\4\2\0\1\4\1\126\3\0"+
    "\4\4\11\0\3\4\1\127\1\74\2\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\11\4\1\0\4\4"+
    "\2\0\2\4\3\0\4\4\11\0\7\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\3\4\1\130\1\131"+
    "\4\4\1\0\1\132\3\4\2\0\2\4\3\0\4\4"+
    "\11\0\7\4\4\0\10\4\1\0\1\133\1\4\1\0"+
    "\2\4\3\0\4\4\1\134\4\4\1\0\4\4\2\0"+
    "\2\4\3\0\4\4\11\0\7\4\4\0\10\4\1\0"+
    "\1\135\1\4\1\0\2\4\3\0\11\4\1\0\4\4"+
    "\2\0\2\4\3\0\4\4\24\0\1\136\62\0\7\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\11\4"+
    "\1\0\4\4\2\0\2\4\3\0\1\4\1\126\2\4"+
    "\77\0\1\21\2\0\1\21\72\0\1\21\75\0\1\21"+
    "\3\0\1\21\27\0\1\137\110\0\1\140\35\0\10\53"+
    "\3\0\11\53\1\0\2\53\1\0\2\53\1\0\1\53"+
    "\1\0\31\53\6\0\1\53\4\0\1\55\12\0\1\55"+
    "\2\0\1\55\3\0\2\141\1\0\2\142\33\0\1\55"+
    "\10\0\3\53\1\143\4\53\3\0\3\53\1\143\2\53"+
    "\1\143\2\53\1\0\2\53\1\144\2\53\1\0\1\53"+
    "\1\0\30\53\1\143\6\0\1\53\2\0\7\4\4\0"+
    "\10\4\1\0\2\4\1\0\2\4\3\0\3\4\1\145"+
    "\1\4\1\146\3\4\1\0\4\4\2\0\2\4\3\0"+
    "\4\4\11\0\7\4\4\0\10\4\1\0\2\4\1\0"+
    "\2\4\3\0\4\4\1\147\4\4\1\0\4\4\2\0"+
    "\2\4\3\0\4\4\11\0\7\4\4\0\10\4\1\0"+
    "\2\4\1\0\2\4\3\0\10\4\1\150\1\0\4\4"+
    "\2\0\2\4\3\0\4\4\10\0\12\12\1\0\63\12"+
    "\12\13\1\0\63\13\1\0\7\4\4\0\10\4\1\0"+
    "\2\4\1\0\2\4\3\0\4\4\1\151\4\4\1\0"+
    "\4\4\2\0\2\4\3\0\4\4\11\0\7\4\4\0"+
    "\10\4\1\0\2\4\1\0\2\4\3\0\11\4\1\0"+
    "\4\4\2\0\1\4\1\152\3\0\4\4\10\0\3\53"+
    "\1\70\4\53\3\0\3\53\1\70\2\53\1\70\2\53"+
    "\1\55\2\56\1\0\2\57\1\0\1\53\1\0\30\53"+
    "\1\70\6\0\1\53\1\0\3\53\1\70\4\53\3\0"+
    "\1\53\2\54\1\71\2\53\1\71\2\53\1\55\2\56"+
    "\1\0\2\57\1\0\1\53\1\0\30\53\1\71\6\0"+
    "\1\53\1\0\3\53\1\153\4\53\3\0\3\53\1\153"+
    "\2\53\3\153\1\0\2\153\1\0\2\53\1\0\1\53"+
    "\1\0\1\53\1\153\1\53\1\153\7\53\1\153\1\53"+
    "\1\153\12\53\1\153\6\0\1\53\2\0\7\4\4\0"+
    "\10\4\1\0\2\4\1\0\2\4\3\0\1\4\1\154"+
    "\7\4\1\0\4\4\2\0\2\4\3\0\4\4\11\0"+
    "\7\4\4\0\10\4\1\0\2\4\1\0\2\4\3\0"+
    "\10\4\1\34\1\0\4\4\2\0\1\155\1\4\3\0"+
    "\4\4\11\0\7\4\4\0\10\4\1\0\2\4\1\0"+
    "\2\4\3\0\7\4\1\156\1\4\1\0\4\4\2\0"+
    "\2\4\3\0\4\4\11\0\7\4\4\0\1\157\7\4"+
    "\1\0\2\4\1\0\2\4\3\0\7\4\1\160\1\4"+
    "\1\0\4\4\2\0\2\4\3\0\4\4\11\0\4\4"+
    "\1\161\2\4\4\0\6\4\1\162\1\4\1\0\2\4"+
    "\1\0\2\4\3\0\1\121\10\4\1\0\4\4\2\0"+
    "\2\4\3\0\4\4\11\0\7\4\4\0\10\4\1\0"+
    "\2\4\1\0\2\4\3\0\1\126\6\4\1\163\1\4"+
    "\1\0\4\4\2\0\2\4\3\0\4\4\11\0\3\4"+
    "\1\164\3\4\4\0\10\4\1\0\2\4\1\0\2\4"+
    "\3\0\11\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\3\4\1\74\3\4\4\0\10\4\1\0\2\4"+
    "\1\0\2\4\3\0\11\4\1\0\4\4\2\0\2\4"+
    "\3\0\4\4\11\0\3\4\1\165\3\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\1\4\1\166\7\4"+
    "\1\0\4\4\2\0\2\4\3\0\4\4\11\0\7\4"+
    "\4\0\10\4\1\0\1\167\1\4\1\0\2\4\3\0"+
    "\11\4\1\0\4\4\2\0\2\4\3\0\4\4\11\0"+
    "\7\4\4\0\10\4\1\0\2\4\1\0\2\4\3\0"+
    "\1\126\10\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\7\4\4\0\10\4\1\0\1\74\1\4\1\0"+
    "\2\4\3\0\11\4\1\0\4\4\1\170\1\0\2\4"+
    "\3\0\4\4\11\0\4\4\1\112\2\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\6\4\1\171\2\4"+
    "\1\0\4\4\2\0\2\4\3\0\4\4\11\0\7\4"+
    "\4\0\10\4\1\0\2\4\1\0\1\172\1\4\3\0"+
    "\1\173\1\4\1\174\5\4\1\175\1\0\4\4\2\0"+
    "\2\4\3\0\4\4\11\0\7\4\4\0\10\4\1\0"+
    "\2\4\1\0\2\4\3\0\10\4\1\176\1\0\4\4"+
    "\2\0\2\4\3\0\4\4\11\0\7\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\4\4\1\177\1\4"+
    "\1\200\2\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\7\4\4\0\10\4\1\0\2\4\1\0\2\4"+
    "\3\0\7\4\1\177\1\4\1\0\4\4\2\0\2\4"+
    "\3\0\4\4\11\0\7\4\4\0\10\4\1\0\2\4"+
    "\1\0\2\4\3\0\11\4\1\0\3\4\1\74\2\0"+
    "\2\4\3\0\4\4\11\0\7\4\4\0\10\4\1\0"+
    "\2\4\1\0\2\4\3\0\11\4\1\0\4\4\2\0"+
    "\1\4\1\201\3\0\4\4\11\0\3\4\1\202\3\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\1\203"+
    "\10\4\1\0\4\4\2\0\2\4\3\0\4\4\11\0"+
    "\7\4\4\0\10\4\1\0\2\4\1\0\2\4\3\0"+
    "\1\204\10\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\7\4\4\0\10\4\1\0\2\4\1\0\2\4"+
    "\3\0\11\4\1\0\1\74\3\4\2\0\2\4\3\0"+
    "\4\4\57\0\1\170\27\0\7\4\4\0\3\4\1\205"+
    "\4\4\1\0\2\4\1\0\2\4\3\0\11\4\1\0"+
    "\4\4\2\0\2\4\3\0\4\4\11\0\7\4\4\0"+
    "\10\4\1\0\2\4\1\0\2\4\3\0\5\4\1\206"+
    "\3\4\1\0\4\4\2\0\2\4\3\0\4\4\11\0"+
    "\7\4\4\0\10\4\1\0\2\4\1\0\2\4\3\0"+
    "\6\4\1\121\2\4\1\0\4\4\2\0\2\4\3\0"+
    "\4\4\11\0\7\4\4\0\10\4\1\0\2\4\1\0"+
    "\2\4\3\0\11\4\1\0\3\4\1\207\2\0\2\4"+
    "\3\0\4\4\11\0\7\4\4\0\1\210\7\4\1\0"+
    "\2\4\1\0\2\4\3\0\1\4\1\202\7\4\1\0"+
    "\4\4\1\0\1\211\2\4\3\0\4\4\11\0\7\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\5\4"+
    "\1\74\3\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\3\4\1\212\3\4\4\0\10\4\1\0\2\4"+
    "\1\0\2\4\3\0\11\4\1\0\4\4\2\0\2\4"+
    "\3\0\4\4\11\0\7\4\4\0\10\4\1\0\2\4"+
    "\1\0\2\4\3\0\1\213\10\4\1\0\4\4\2\0"+
    "\2\4\3\0\4\4\11\0\7\4\4\0\10\4\1\0"+
    "\2\4\1\0\2\4\3\0\2\4\1\214\6\4\1\0"+
    "\4\4\2\0\2\4\3\0\4\4\11\0\3\4\1\215"+
    "\3\4\4\0\10\4\1\0\2\4\1\0\2\4\3\0"+
    "\11\4\1\0\4\4\2\0\2\4\3\0\4\4\55\0"+
    "\1\216\34\0\1\217\105\0\1\220\64\0\1\221\12\0"+
    "\1\221\2\0\1\221\5\0\1\144\35\0\1\221\10\0"+
    "\3\53\1\143\4\53\3\0\3\53\1\143\2\53\1\143"+
    "\2\53\1\0\2\53\1\0\2\57\1\0\1\53\1\0"+
    "\30\53\1\143\6\0\1\53\4\0\1\221\12\0\1\221"+
    "\2\0\1\221\43\0\1\221\11\0\7\4\4\0\1\126"+
    "\7\4\1\0\2\4\1\0\2\4\3\0\11\4\1\0"+
    "\4\4\2\0\2\4\3\0\4\4\11\0\7\4\4\0"+
    "\10\4\1\0\2\4\1\0\2\4\3\0\11\4\1\0"+
    "\1\222\3\4\2\0\2\4\3\0\4\4\11\0\7\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\11\4"+
    "\1\0\4\4\2\0\2\4\3\0\2\4\1\126\1\4"+
    "\11\0\7\4\4\0\10\4\1\0\2\4\1\0\2\4"+
    "\3\0\4\4\1\223\4\4\1\0\4\4\2\0\2\4"+
    "\3\0\4\4\11\0\7\4\4\0\10\4\1\0\2\4"+
    "\1\0\2\4\3\0\3\4\1\224\5\4\1\0\4\4"+
    "\2\0\2\4\3\0\4\4\11\0\7\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\11\4\1\0\4\4"+
    "\2\0\2\4\3\0\3\4\1\225\10\0\3\53\1\153"+
    "\4\53\3\0\1\53\2\54\1\153\2\53\3\153\1\0"+
    "\2\153\1\0\2\53\1\0\1\53\1\0\1\53\1\153"+
    "\1\53\1\153\7\53\1\153\1\53\1\153\12\53\1\153"+
    "\6\0\1\53\2\0\3\4\1\226\3\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\11\4\1\0\1\227"+
    "\3\4\2\0\2\4\3\0\4\4\11\0\4\4\1\230"+
    "\2\4\4\0\10\4\1\0\2\4\1\0\2\4\3\0"+
    "\11\4\1\0\4\4\2\0\2\4\3\0\4\4\11\0"+
    "\7\4\4\0\10\4\1\0\2\4\1\0\2\4\3\0"+
    "\10\4\1\231\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\7\4\4\0\10\4\1\0\1\232\1\4\1\0"+
    "\2\4\3\0\11\4\1\0\4\4\2\0\2\4\3\0"+
    "\4\4\11\0\7\4\4\0\10\4\1\0\2\4\1\0"+
    "\2\4\3\0\3\4\1\233\5\4\1\234\4\4\2\0"+
    "\2\4\3\0\4\4\11\0\7\4\4\0\10\4\1\0"+
    "\2\4\1\0\2\4\3\0\11\4\1\0\3\4\1\235"+
    "\2\0\2\4\3\0\4\4\11\0\7\4\4\0\10\4"+
    "\1\0\1\236\1\4\1\0\2\4\3\0\11\4\1\0"+
    "\4\4\2\0\2\4\3\0\4\4\11\0\7\4\4\0"+
    "\10\4\1\0\2\4\1\0\2\4\3\0\10\4\1\237"+
    "\1\0\4\4\2\0\2\4\3\0\4\4\11\0\7\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\11\4"+
    "\1\0\3\4\1\240\2\0\2\4\3\0\4\4\11\0"+
    "\7\4\4\0\10\4\1\0\1\241\1\4\1\0\2\4"+
    "\3\0\11\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\7\4\4\0\10\4\1\0\2\4\1\0\2\4"+
    "\3\0\1\242\1\243\6\4\1\244\1\0\4\4\2\0"+
    "\2\4\3\0\4\4\11\0\7\4\4\0\10\4\1\0"+
    "\2\4\1\0\2\4\3\0\3\4\1\245\5\4\1\0"+
    "\4\4\2\0\2\4\3\0\4\4\11\0\3\4\1\246"+
    "\3\4\4\0\10\4\1\0\2\4\1\0\2\4\3\0"+
    "\11\4\1\0\4\4\2\0\2\4\3\0\4\4\11\0"+
    "\7\4\4\0\10\4\1\0\1\156\1\4\1\0\2\4"+
    "\3\0\11\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\7\4\4\0\10\4\1\0\2\4\1\0\2\4"+
    "\3\0\4\4\1\247\4\4\1\0\4\4\2\0\2\4"+
    "\3\0\4\4\11\0\7\4\4\0\10\4\1\0\1\250"+
    "\1\4\1\0\2\4\3\0\11\4\1\0\4\4\2\0"+
    "\2\4\3\0\4\4\11\0\7\4\4\0\10\4\1\0"+
    "\1\251\1\4\1\0\2\4\3\0\11\4\1\0\4\4"+
    "\2\0\2\4\3\0\4\4\11\0\7\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\3\4\1\252\5\4"+
    "\1\0\4\4\2\0\2\4\2\0\1\253\4\4\11\0"+
    "\4\4\1\254\2\4\4\0\10\4\1\0\2\4\1\0"+
    "\2\4\3\0\11\4\1\0\4\4\2\0\2\4\1\0"+
    "\1\255\1\0\4\4\11\0\7\4\4\0\10\4\1\0"+
    "\1\256\1\4\1\0\2\4\3\0\11\4\1\0\4\4"+
    "\2\0\2\4\3\0\4\4\11\0\4\4\1\257\2\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\11\4"+
    "\1\0\4\4\2\0\2\4\3\0\4\4\11\0\7\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\3\4"+
    "\1\74\5\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\7\4\4\0\10\4\1\0\2\4\1\0\2\4"+
    "\3\0\1\121\10\4\1\0\4\4\2\0\2\4\3\0"+
    "\4\4\11\0\7\4\4\0\10\4\1\0\2\4\1\0"+
    "\2\4\3\0\4\4\1\260\4\4\1\0\4\4\2\0"+
    "\2\4\3\0\4\4\11\0\7\4\4\0\10\4\1\0"+
    "\2\4\1\0\2\4\3\0\10\4\1\261\1\0\4\4"+
    "\2\0\2\4\3\0\4\4\11\0\7\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\3\4\1\262\5\4"+
    "\1\0\4\4\2\0\2\4\3\0\4\4\11\0\7\4"+
    "\4\0\10\4\1\0\1\263\1\4\1\0\2\4\3\0"+
    "\11\4\1\0\4\4\2\0\2\4\3\0\4\4\11\0"+
    "\7\4\4\0\10\4\1\0\2\4\1\0\2\4\3\0"+
    "\1\261\10\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\50\0\1\170\36\0\7\4\4\0\10\4\1\0\2\4"+
    "\1\0\2\4\3\0\5\4\1\264\3\4\1\0\4\4"+
    "\2\0\2\4\3\0\4\4\11\0\7\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\1\4\1\265\7\4"+
    "\1\0\4\4\2\0\2\4\3\0\4\4\11\0\7\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\4\4"+
    "\1\161\4\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\7\4\4\0\10\4\1\0\1\74\1\4\1\0"+
    "\2\4\3\0\11\4\1\0\4\4\2\0\2\4\3\0"+
    "\4\4\51\0\1\266\106\0\1\267\60\0\1\270\43\0"+
    "\1\221\12\0\1\221\2\0\1\221\6\0\2\142\33\0"+
    "\1\221\11\0\7\4\4\0\10\4\1\0\2\4\1\0"+
    "\2\4\3\0\2\4\1\235\6\4\1\0\4\4\2\0"+
    "\2\4\3\0\4\4\11\0\7\4\4\0\10\4\1\0"+
    "\2\4\1\0\2\4\3\0\5\4\1\261\3\4\1\0"+
    "\4\4\2\0\2\4\3\0\4\4\11\0\1\271\6\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\11\4"+
    "\1\0\4\4\2\0\2\4\3\0\4\4\11\0\7\4"+
    "\4\0\2\4\1\126\5\4\1\0\2\4\1\0\2\4"+
    "\3\0\11\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\7\4\4\0\10\4\1\0\1\272\1\4\1\0"+
    "\2\4\3\0\11\4\1\0\4\4\2\0\2\4\3\0"+
    "\4\4\11\0\7\4\4\0\1\273\7\4\1\0\2\4"+
    "\1\0\2\4\3\0\11\4\1\0\4\4\2\0\2\4"+
    "\3\0\4\4\11\0\7\4\4\0\10\4\1\0\2\4"+
    "\1\0\2\4\3\0\3\4\1\274\5\4\1\0\4\4"+
    "\2\0\2\4\3\0\4\4\11\0\7\4\4\0\10\4"+
    "\1\0\1\126\1\4\1\0\2\4\3\0\11\4\1\0"+
    "\4\4\2\0\2\4\3\0\4\4\11\0\7\4\4\0"+
    "\10\4\1\0\2\4\1\0\2\4\3\0\1\4\1\275"+
    "\7\4\1\0\4\4\2\0\2\4\3\0\4\4\11\0"+
    "\7\4\4\0\10\4\1\0\2\4\1\0\2\4\3\0"+
    "\11\4\1\0\1\276\3\4\2\0\2\4\3\0\4\4"+
    "\57\0\1\277\27\0\7\4\4\0\10\4\1\0\2\4"+
    "\1\0\2\4\3\0\3\4\1\126\5\4\1\0\4\4"+
    "\2\0\2\4\3\0\4\4\11\0\7\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\1\4\1\235\7\4"+
    "\1\0\4\4\1\0\1\300\2\4\3\0\4\4\11\0"+
    "\7\4\4\0\10\4\1\0\1\301\1\4\1\0\2\4"+
    "\3\0\11\4\1\0\4\4\1\302\1\0\2\4\3\0"+
    "\4\4\11\0\7\4\4\0\10\4\1\0\2\4\1\0"+
    "\2\4\3\0\3\4\1\303\5\4\1\0\4\4\2\0"+
    "\2\4\3\0\4\4\11\0\7\4\4\0\10\4\1\0"+
    "\2\4\1\0\2\4\3\0\11\4\1\0\3\4\1\304"+
    "\2\0\2\4\3\0\4\4\11\0\7\4\4\0\10\4"+
    "\1\0\1\305\1\4\1\0\2\4\3\0\11\4\1\0"+
    "\4\4\2\0\2\4\3\0\4\4\11\0\7\4\4\0"+
    "\10\4\1\0\2\4\1\0\2\4\3\0\11\4\1\0"+
    "\1\106\3\4\2\0\2\4\3\0\4\4\11\0\7\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\11\4"+
    "\1\0\4\4\2\0\1\4\1\126\3\0\4\4\11\0"+
    "\7\4\4\0\10\4\1\0\2\4\1\0\2\4\3\0"+
    "\10\4\1\306\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\7\4\4\0\10\4\1\0\2\4\1\0\2\4"+
    "\3\0\4\4\1\307\4\4\1\0\4\4\2\0\2\4"+
    "\3\0\4\4\11\0\3\4\1\235\3\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\11\4\1\0\4\4"+
    "\2\0\2\4\3\0\4\4\11\0\3\4\1\310\3\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\11\4"+
    "\1\0\4\4\2\0\2\4\3\0\4\4\11\0\7\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\4\4"+
    "\1\311\4\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\7\4\4\0\10\4\1\0\2\4\1\0\2\4"+
    "\3\0\2\4\1\312\6\4\1\0\4\4\2\0\2\4"+
    "\3\0\4\4\47\0\1\313\37\0\7\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\1\314\10\4\1\0"+
    "\4\4\2\0\2\4\3\0\4\4\45\0\1\315\41\0"+
    "\7\4\4\0\10\4\1\0\2\4\1\0\2\4\3\0"+
    "\4\4\1\231\4\4\1\0\4\4\2\0\2\4\3\0"+
    "\4\4\11\0\7\4\4\0\10\4\1\0\1\316\1\4"+
    "\1\0\2\4\3\0\11\4\1\0\4\4\2\0\2\4"+
    "\3\0\4\4\11\0\7\4\4\0\10\4\1\0\2\4"+
    "\1\0\2\4\3\0\1\4\1\317\7\4\1\0\4\4"+
    "\1\0\1\320\2\4\3\0\4\4\11\0\7\4\4\0"+
    "\10\4\1\0\2\4\1\0\2\4\3\0\11\4\1\0"+
    "\1\126\3\4\2\0\2\4\3\0\4\4\11\0\7\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\7\4"+
    "\1\321\1\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\7\4\4\0\10\4\1\0\2\4\1\0\2\4"+
    "\3\0\7\4\1\231\1\4\1\0\4\4\2\0\2\4"+
    "\3\0\4\4\11\0\7\4\4\0\10\4\1\0\2\4"+
    "\1\0\2\4\3\0\3\4\1\205\5\4\1\0\4\4"+
    "\2\0\2\4\3\0\4\4\11\0\7\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\3\4\1\322\5\4"+
    "\1\0\4\4\2\0\2\4\3\0\4\4\52\0\1\270"+
    "\73\0\1\323\104\0\1\324\27\0\7\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\4\4\1\325\2\4"+
    "\1\326\1\327\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\7\4\4\0\10\4\1\0\2\4\1\0\2\4"+
    "\3\0\2\4\1\202\6\4\1\0\4\4\2\0\2\4"+
    "\3\0\4\4\11\0\7\4\4\0\10\4\1\0\2\4"+
    "\1\0\2\4\3\0\11\4\1\0\2\4\1\202\1\4"+
    "\2\0\2\4\3\0\4\4\11\0\7\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\7\4\1\330\1\4"+
    "\1\0\4\4\2\0\2\4\3\0\4\4\11\0\7\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\4\4"+
    "\1\331\4\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\7\4\4\0\10\4\1\0\2\4\1\0\2\4"+
    "\3\0\1\215\10\4\1\0\4\4\2\0\2\4\3\0"+
    "\4\4\45\0\1\332\100\0\1\324\36\0\7\4\4\0"+
    "\10\4\1\0\2\4\1\0\2\4\3\0\5\4\1\126"+
    "\3\4\1\0\4\4\2\0\2\4\3\0\4\4\52\0"+
    "\1\324\34\0\7\4\4\0\10\4\1\0\2\4\1\0"+
    "\2\4\3\0\11\4\1\0\3\4\1\175\2\0\2\4"+
    "\3\0\4\4\11\0\7\4\4\0\10\4\1\0\2\4"+
    "\1\0\2\4\3\0\11\4\1\0\1\333\3\4\2\0"+
    "\2\4\3\0\4\4\11\0\7\4\4\0\10\4\1\0"+
    "\2\4\1\0\2\4\3\0\7\4\1\126\1\4\1\0"+
    "\4\4\2\0\2\4\3\0\4\4\11\0\7\4\4\0"+
    "\10\4\1\0\2\4\1\0\2\4\3\0\11\4\1\0"+
    "\4\4\2\0\2\4\1\334\2\0\4\4\11\0\7\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\5\4"+
    "\1\202\3\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\7\4\4\0\10\4\1\0\2\4\1\0\2\4"+
    "\3\0\10\4\1\235\1\0\4\4\2\0\2\4\3\0"+
    "\4\4\11\0\3\4\1\261\3\4\4\0\10\4\1\0"+
    "\2\4\1\0\2\4\3\0\11\4\1\0\4\4\2\0"+
    "\2\4\3\0\4\4\11\0\7\4\4\0\10\4\1\0"+
    "\1\145\1\4\1\0\2\4\3\0\11\4\1\0\4\4"+
    "\2\0\2\4\3\0\4\4\35\0\1\335\51\0\7\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\1\4"+
    "\1\336\7\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\46\0\1\337\40\0\1\340\6\4\4\0\10\4\1\0"+
    "\2\4\1\0\2\4\3\0\11\4\1\0\4\4\2\0"+
    "\2\4\3\0\4\4\11\0\7\4\4\0\10\4\1\0"+
    "\2\4\1\0\2\4\3\0\3\4\1\261\5\4\1\0"+
    "\4\4\2\0\2\4\3\0\4\4\56\0\1\270\30\0"+
    "\7\4\4\0\10\4\1\0\2\4\1\0\2\4\3\0"+
    "\11\4\1\0\2\4\1\261\1\4\2\0\2\4\3\0"+
    "\4\4\11\0\3\4\1\341\3\4\4\0\10\4\1\0"+
    "\2\4\1\0\2\4\3\0\11\4\1\0\4\4\2\0"+
    "\2\4\3\0\4\4\62\0\1\342\24\0\7\4\4\0"+
    "\10\4\1\0\2\4\1\0\2\4\3\0\7\4\1\343"+
    "\1\4\1\0\4\4\2\0\2\4\3\0\4\4\11\0"+
    "\4\4\1\344\2\4\4\0\10\4\1\0\2\4\1\0"+
    "\2\4\3\0\11\4\1\0\4\4\2\0\2\4\3\0"+
    "\4\4\11\0\7\4\4\0\10\4\1\0\1\123\1\4"+
    "\1\0\2\4\3\0\11\4\1\0\4\4\2\0\2\4"+
    "\3\0\4\4\11\0\7\4\4\0\10\4\1\0\2\4"+
    "\1\0\2\4\3\0\10\4\1\121\1\0\4\4\2\0"+
    "\2\4\3\0\4\4\11\0\7\4\4\0\10\4\1\0"+
    "\2\4\1\0\2\4\3\0\11\4\1\0\1\263\3\4"+
    "\2\0\2\4\3\0\4\4\35\0\1\170\51\0\7\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\7\4"+
    "\1\345\1\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\14\0\1\346\105\0\1\324\62\0\4\4\1\347\2\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\11\4"+
    "\1\0\4\4\2\0\2\4\3\0\4\4\15\0\1\350"+
    "\71\0\7\4\4\0\10\4\1\0\1\351\1\4\1\0"+
    "\2\4\3\0\11\4\1\0\4\4\2\0\2\4\3\0"+
    "\4\4\11\0\7\4\4\0\10\4\1\0\2\4\1\0"+
    "\2\4\3\0\10\4\1\257\1\0\4\4\2\0\2\4"+
    "\3\0\4\4\35\0\1\352\51\0\7\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\10\4\1\353\1\0"+
    "\4\4\2\0\2\4\3\0\4\4\11\0\7\4\4\0"+
    "\10\4\1\0\2\4\1\0\2\4\3\0\5\4\1\354"+
    "\3\4\1\0\4\4\2\0\2\4\3\0\4\4\11\0"+
    "\7\4\4\0\10\4\1\0\2\4\1\0\2\4\3\0"+
    "\11\4\1\0\3\4\1\231\2\0\2\4\3\0\4\4"+
    "\51\0\1\270\35\0\7\4\4\0\1\261\7\4\1\0"+
    "\2\4\1\0\2\4\3\0\11\4\1\0\4\4\2\0"+
    "\2\4\3\0\4\4\24\0\1\270\62\0\7\4\4\0"+
    "\10\4\1\0\2\4\1\0\2\4\3\0\7\4\1\355"+
    "\1\4\1\0\4\4\2\0\2\4\3\0\4\4\51\0"+
    "\1\356\35\0\7\4\4\0\10\4\1\0\1\357\1\4"+
    "\1\0\2\4\3\0\11\4\1\0\4\4\2\0\2\4"+
    "\3\0\4\4\11\0\7\4\4\0\10\4\1\0\1\311"+
    "\1\4\1\0\2\4\3\0\11\4\1\0\4\4\2\0"+
    "\2\4\3\0\4\4\11\0\7\4\4\0\10\4\1\0"+
    "\2\4\1\0\2\4\3\0\11\4\1\0\4\4\2\0"+
    "\1\360\1\4\3\0\4\4\14\0\1\270\72\0\7\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\4\4"+
    "\1\361\4\4\1\0\4\4\2\0\2\4\3\0\4\4"+
    "\11\0\4\4\1\362\2\4\4\0\10\4\1\0\2\4"+
    "\1\0\2\4\3\0\11\4\1\0\4\4\2\0\2\4"+
    "\3\0\4\4\11\0\3\4\1\363\3\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\11\4\1\0\4\4"+
    "\2\0\2\4\3\0\4\4\11\0\7\4\4\0\10\4"+
    "\1\0\2\4\1\0\2\4\3\0\3\4\1\364\5\4"+
    "\1\0\4\4\2\0\2\4\3\0\4\4\11\0\7\4"+
    "\4\0\10\4\1\0\2\4\1\0\2\4\3\0\11\4"+
    "\1\0\1\365\3\4\2\0\2\4\3\0\4\4\11\0"+
    "\7\4\4\0\10\4\1\0\2\4\1\0\2\4\3\0"+
    "\7\4\1\205\1\4\1\0\4\4\2\0\2\4\3\0"+
    "\4\4\10\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[14384];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\11\10\1\1\11\4\1\1\11\22\1\1\11"+
    "\3\1\1\11\2\0\10\1\1\11\2\0\34\1\1\0"+
    "\13\1\4\0\1\11\1\1\1\0\23\1\1\11\20\1"+
    "\1\0\4\1\3\0\13\1\1\0\16\1\1\0\1\1"+
    "\1\0\10\1\3\0\7\1\1\0\1\1\1\0\10\1"+
    "\1\0\1\1\1\0\2\1\1\0\2\1\1\0\1\11"+
    "\5\1\1\0\1\1\2\0\1\1\1\0\2\1\1\0"+
    "\3\1\1\0\1\1\1\0\1\1\1\0\3\1\1\0"+
    "\7\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[245];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /* user code: */


	/**
	 * Constructor.  This must be here because JFlex does not generate a
	 * no-parameter constructor.
	 */
	public PotigolTokenMaker() {
		super();
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param tokenType The token's type.
	 */
	private void addToken(int tokenType) {
		addToken(zzStartRead, zzMarkedPos-1, tokenType);
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param tokenType The token's type.
	 */
	private void addToken(int start, int end, int tokenType) {
		int so = start + offsetShift;
		addToken(zzBuffer, start,end, tokenType, so);
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param array The character array.
	 * @param start The starting offset in the array.
	 * @param end The ending offset in the array.
	 * @param tokenType The token's type.
	 * @param startOffset The offset in the document at which this token
	 *                    occurs.
	 */
	@Override
	public void addToken(char[] array, int start, int end, int tokenType, int startOffset) {
		super.addToken(array, start,end, tokenType, startOffset);
		zzStartRead = zzMarkedPos;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] getLineCommentStartAndEnd(int languageIndex) {
		return new String[] { "#", null };
	}


	/**
	 * Returns the first token in the linked list of tokens generated
	 * from <code>text</code>.  This method must be implemented by
	 * subclasses so they can correctly implement syntax highlighting.
	 *
	 * @param text The text from which to get tokens.
	 * @param initialTokenType The token type we should start with.
	 * @param startOffset The offset into the document at which
	 *        <code>text</code> starts.
	 * @return The first <code>Token</code> in a linked list representing
	 *         the syntax highlighted text.
	 */
	public Token getTokenList(Segment text, int initialTokenType, int startOffset) {

		resetTokenList();
		this.offsetShift = -text.offset + startOffset;

		// Start off in the proper state.
		int state = Token.NULL;
		switch (initialTokenType) {
			case Token.LITERAL_STRING_DOUBLE_QUOTE:
				state = LONG_STRING_2;
				break;
			case Token.LITERAL_CHAR:
				state = LONG_STRING_1;
				break;
			default:
				state = Token.NULL;
		}

		s = text;
		try {
			yyreset(zzReader);
			yybegin(state);
			return yylex();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return new TokenImpl();
		}

	}


	/**
	 * Resets the scanner to read from a new input stream.
	 * Does not close the old reader.
	 *
	 * All internal variables are reset, the old input stream 
	 * <b>cannot</b> be reused (internal buffer is discarded and lost).
	 * Lexical state is set to <tt>YY_INITIAL</tt>.
	 *
	 * @param reader   the new input stream 
	 */
	public final void yyreset(Reader reader) {
		// 's' has been updated.
		zzBuffer = s.array;
		/*
		 * We replaced the line below with the two below it because zzRefill
		 * no longer "refills" the buffer (since the way we do it, it's always
		 * "full" the first time through, since it points to the segment's
		 * array).  So, we assign zzEndRead here.
		 */
		//zzStartRead = zzEndRead = s.offset;
		zzStartRead = s.offset;
		zzEndRead = zzStartRead + s.count - 1;
		zzCurrentPos = zzMarkedPos = zzPushbackPos = s.offset;
		zzLexicalState = YYINITIAL;
		zzReader = reader;
		zzAtBOL  = true;
		zzAtEOF  = false;
	}


	/**
	 * Refills the input buffer.
	 *
	 * @return      <code>true</code> if EOF was reached, otherwise
	 *              <code>false</code>.
	 */
	private boolean zzRefill() {
		return zzCurrentPos>=s.offset+s.count;
	}




  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public PotigolTokenMaker(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public PotigolTokenMaker(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 198) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }
    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }

  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public org.fife.ui.rsyntaxtextarea.Token yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 7: 
          { addToken(Token.RESERVED_WORD);
          }
        case 14: break;
        case 2: 
          { addToken(Token.IDENTIFIER);
          }
        case 15: break;
        case 13: 
          { addToken(Token.FUNCTION);
          }
        case 16: break;
        case 12: 
          { addToken(Token.LITERAL_NUMBER_FLOAT);
          }
        case 17: break;
        case 4: 
          { addToken(Token.LITERAL_STRING_DOUBLE_QUOTE);
          }
        case 18: break;
        case 9: 
          { addToken(Token.WHITESPACE);
          }
        case 19: break;
        case 11: 
          { addToken(Token.ERROR_NUMBER_FORMAT);
          }
        case 20: break;
        case 8: 
          { addToken(Token.COMMENT_EOL);
          }
        case 21: break;
        case 1: 
          { addToken(Token.ERROR_IDENTIFIER);
          }
        case 22: break;
        case 6: 
          { addToken(Token.OPERATOR);
          }
        case 23: break;
        case 3: 
          { addToken(Token.LITERAL_NUMBER_DECIMAL_INT);
          }
        case 24: break;
        case 5: 
          { addNullToken(); return firstToken;
          }
        case 25: break;
        case 10: 
          { addToken(Token.SEPARATOR);
          }
        case 26: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            switch (zzLexicalState) {
            case YYINITIAL: {
              addNullToken(); return firstToken;
            }
            case 246: break;
            case LONG_STRING_2: {
              if (firstToken==null) {
										addToken(Token.LITERAL_STRING_DOUBLE_QUOTE); 
									}
									return firstToken;
            }
            case 247: break;
            default:
            return null;
            }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}

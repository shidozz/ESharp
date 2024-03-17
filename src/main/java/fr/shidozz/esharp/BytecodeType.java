package fr.shidozz.esharp;

/**
 *
 * @author shidozz
 */
public enum BytecodeType {
    NOP,
    PUSH,
    ADD,
    SUB,
    MUL,
    DIV,
    JUMP,
    JUMP_IF_FALSE,
    JUMP_IF_TRUE,
    DECLARE_VAR,
    STORE_VAR,
    LOAD_VAR,
    DECLARE_FUNCTION,
    CALL_FUNCTION,
    RETURN,
    SEPARE_PARAM,
    HALT;
}
